package com.skypro.FirstTeamPetShelter.service.bot.Impl;

import com.pengrad.telegrambot.model.request.*;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.service.ShelterService;
import com.skypro.FirstTeamPetShelter.service.bot.BotMenuService;
import com.skypro.FirstTeamPetShelter.enums.Menu;
import com.skypro.FirstTeamPetShelter.service.bot.helper.BotHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotMenuServiceImpl implements BotMenuService {
    private final ShelterService shelterService;
    private final BotHelper botHelper;

    public BotMenuServiceImpl(ShelterService shelterService, BotHelper botHelper) {
        this.shelterService = shelterService;
        this.botHelper = botHelper;
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
            case SHELTER_BASE -> {
                return getShelterBaseMenu();
            }
            case SHELTER_DOGS -> {
                //return getShelterDogMenu();
            }
            case SHELTER_CATS -> {
                //return getShelterCatMenu();
            }
        }
        return null;
    }

    private InlineKeyboardMarkup getShelterBaseMenu() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("⌚Расписание и \uD83D\uDEE3схема проезда").callbackData("ShelterInfo"), new InlineKeyboardButton("Контакты для проезда").callbackData("ShelterSecurity"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("ТБ на территории").callbackData("ShelterSafety"), new InlineKeyboardButton("Выбрать животное").callbackData("ShelterFindPet"));
        inlineKeyboardMarkup.addRow(new InlineKeyboardButton("Оставить номер для связи").callbackData("PhoneNumber"));

        return inlineKeyboardMarkup;
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
        List<Report> reports = botHelper.getAdoptersReportCheck();
        InlineKeyboardButton[] inlineKeyboardButtons = reports.stream().map(report -> new InlineKeyboardButton(
                "Отчёт усыновителя " + report.getAdopter().getAdopterName())
                .callbackData("Report_" + report.getId()
                )).toArray(InlineKeyboardButton[]::new);
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getUsersBecomeAdoptiveMenu() {
        List<UserApp> users = botHelper.getUsersBecomeAdoptive();
        InlineKeyboardButton[] inlineKeyboardButtons = users.stream().map(userApp -> new InlineKeyboardButton(
                        userApp.getUserName() + " Телефон: " + userApp.getUserPhoneNumber())
                        .callbackData("UserBecomeAdoptive_" + userApp.getUserTelegramId()
                        )).toArray(InlineKeyboardButton[]::new);
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getCallingUsersMenu() {
        List<UserApp> users = botHelper.getUsersCallingVolunteer();
        InlineKeyboardButton[] inlineKeyboardButtons = users.stream().map(userApp -> new InlineKeyboardButton(
                userApp.getUserName() + " Телефон: " + userApp.getUserPhoneNumber())
                .callbackData("UsersCall_" + userApp.getUserTelegramId()
                )).toArray(InlineKeyboardButton[]::new);
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getCallingAdoptersMenu() {
        List<Adopter> adopters = botHelper.getAdoptersCallingVolunteer();
        InlineKeyboardButton[] inlineKeyboardButtons = adopters.stream().map(adopter -> new InlineKeyboardButton(
                        adopter.getAdopterName() + " Телефон: " + adopter.getAdopterPhoneNumber())
                        .callbackData("AdoptersCall_" + adopter.getAdopterTelegramId()
                        )).toArray(InlineKeyboardButton[]::new);
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getStartMenu() {
        List<Shelter> shelters = shelterService.getAllShelters().stream().toList();
        InlineKeyboardButton[] inlineKeyboardButtons = shelters.stream().map(shelter -> new InlineKeyboardButton(
                    shelter.getShelterName())
                    .callbackData("Shelter_" + shelter.getId()
                    )).toArray(InlineKeyboardButton[]::new);
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    private InlineKeyboardMarkup getAnswerContactedMenu() {
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[2];
        inlineKeyboardButtons[0] = new InlineKeyboardButton("Не связывались").callbackData("NotContacted");
        inlineKeyboardButtons[1] = new InlineKeyboardButton("Связались").callbackData("Contacted");
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }
}
