package com.skypro.FirstTeamPetShelter.service.bot.Impl;

import com.pengrad.telegrambot.model.request.*;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.service.AdopterService;
import com.skypro.FirstTeamPetShelter.service.ReportService;
import com.skypro.FirstTeamPetShelter.service.ShelterService;
import com.skypro.FirstTeamPetShelter.service.UserService;
import com.skypro.FirstTeamPetShelter.service.bot.BotMenuService;
import com.skypro.FirstTeamPetShelter.enums.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotMenuServiceImpl implements BotMenuService {
    private final ShelterService shelterService;
    private final UserService userService;
    private final AdopterService adopterService;
    private final ReportService reportService;

    public BotMenuServiceImpl(ShelterService shelterService, UserService userService, AdopterService adopterService, ReportService reportService) {
        this.shelterService = shelterService;
        this.userService = userService;
        this.adopterService = adopterService;
        this.reportService = reportService;
    }

    @Override
    public InlineKeyboardMarkup getMenu(Menu menu) {
        switch (menu) {
            case START -> {
                return getStartMenu();
            }
            case ANSWER_CONTACTED -> {
                return getAnswerContactedMenu();
            }
            case CALLING_USERS -> {
                return getCallingUsersMenu();
            }
            case CALLING_ADOPTERS -> {
                return getCallingAdoptersMenu();
            }
            case USERS_BECOME_ADOPTIVE -> {
                return getUsersBecomeAdoptiveMenu();
            }
            case CHECK_REPORTS -> {
                return getCheckReportsMenu();
            }
            case ADOPTER_SEND_REPORT -> {
                return getAdopterSendReportMenu();
            }
            case USERNAME_PHONE_NUMBER -> {
                return getUsernamePhoneNumberMenu();
            }
        }
        return null;
    }

    private InlineKeyboardMarkup getUsernamePhoneNumberMenu() {
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[2];
        inlineKeyboardButtons[0] = new InlineKeyboardButton("Ввести своё имя").callbackData("SendUserName");
        inlineKeyboardButtons[1] = new InlineKeyboardButton("Ввести свой номер телефона").callbackData("SendUserPhone");
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getAdopterSendReportMenu() {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("Сдать отчёт").callbackData("ReportFromAdopter");
        return new InlineKeyboardMarkup(inlineKeyboardButton);
    }

    private InlineKeyboardMarkup getCheckReportsMenu() {
        List<Report> reports = reportService.getAllReports().stream().toList();
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[reports.size()];
        for (int i = 0; i < inlineKeyboardButtons.length; i++) {
            if (reports.get(i).isReviewed()) {
                inlineKeyboardButtons[i] = new InlineKeyboardButton(
                        "Отчёт усыновителя " + reports.get(i).getAdopter().getAdopterName())
                        .callbackData("Report_" + reports.get(i).getId()
                        );
            }
        }
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getUsersBecomeAdoptiveMenu() {
        List<UserApp> users = userService.getAllUser().stream().toList();
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[users.size()];
        for (int i = 0; i < inlineKeyboardButtons.length; i++) {
            if (users.get(i).isBecomeAdoptive()) {
                inlineKeyboardButtons[i] = new InlineKeyboardButton(
                        users.get(i).getUserName() + " Телефон: " + users.get(i).getUserPhoneNumber())
                        .callbackData("UserBecomeAdoptive_" + users.get(i).getUserTelegramId()
                        );
            }
        }
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getCallingUsersMenu() {
        List<UserApp> users = userService.getAllUser().stream().toList();
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[users.size()];
        for (int i = 0; i < inlineKeyboardButtons.length; i++) {
            if (!users.get(i).isContacted()) {
                inlineKeyboardButtons[i] = new InlineKeyboardButton(
                        users.get(i).getUserName() + " Телефон: " + users.get(i).getUserPhoneNumber())
                        .callbackData("UsersCall_" + users.get(i).getUserTelegramId()
                        );
            }
        }
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getCallingAdoptersMenu() {
        List<Adopter> adopters = adopterService.getAllAdopters().stream().toList();
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[adopters.size()];
        for (int i = 0; i < inlineKeyboardButtons.length; i++) {
            if (!adopters.get(i).isContacted()) {
                inlineKeyboardButtons[i] = new InlineKeyboardButton(
                        adopters.get(i).getAdopterName() + " Телефон: " + adopters.get(i).getAdopterPhoneNumber())
                        .callbackData("AdoptersCall_" + adopters.get(i).getAdopterTelegramId()
                        );
            }
        }
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getStartMenu() {
        List<Shelter> shelters = shelterService.getAllShelters().stream().toList();
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[shelters.size()];
        for (int i = 0; i < inlineKeyboardButtons.length; i++) {
            inlineKeyboardButtons[i] = new InlineKeyboardButton(
                    shelters.get(i).getShelterName())
                    .callbackData("Shelter_" + shelters.get(i).getId()
                    );
        }
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getAnswerContactedMenu() {
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[2];
        inlineKeyboardButtons[0] = new InlineKeyboardButton("Не связывались").callbackData("NotContacted");
        inlineKeyboardButtons[1] = new InlineKeyboardButton("Связались").callbackData("Contacted");
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }
}
