package com.skypro.FirstTeamPetShelter.service.bot.Impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.service.AdopterService;
import com.skypro.FirstTeamPetShelter.service.InfoService;
import com.skypro.FirstTeamPetShelter.service.ReportService;
import com.skypro.FirstTeamPetShelter.service.UserService;
import com.skypro.FirstTeamPetShelter.service.bot.BotHandler;
import com.skypro.FirstTeamPetShelter.service.bot.BotService;
import com.skypro.FirstTeamPetShelter.service.bot.helper.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BotHandlerImpl implements BotHandler {
    @Autowired
    private BotService botService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdopterService adopterService;

    @Autowired
    private ReportService reportService;

    @Override
    public void updateHandle(TelegramBot telegramBot, Update update) {
        if (update.message().text().equals("/start")) {
            // проверка пользователя - кто это - новый, юзер, усыновитель или волонтер
            switch (botService.getVisitorRole(update.message().from().id())) {
                case NEWBIE -> {
                    sendMessage("Start", telegramBot, update, Menu.START);
                }
                case USER -> {
                    userUpdateHandle(telegramBot, update);
                }
                case ADOPTER -> {
                    adopterUpdateHandle(telegramBot, update);
                }
                case VOLUNTEER -> {
                    volunteerUpdateHandle(telegramBot, update);
                }
            }
        }
        if (update.message().text().equals("/info")) {
            sendMessage("BotInformation", telegramBot, update, null);
        }
        if (update.message().text().equals("/call-volunteer")) {
            sendMessage("CallingVolunteer", telegramBot, update, null);
            botService.callVolunteer(telegramBot, update);
        }
    }

    @Override
    public void callbackHandle(TelegramBot telegramBot, CallbackQuery callbackQuery) {

    }

    private void volunteerUpdateHandle(TelegramBot telegramBot, Update update) {
        // Приветствие волонтера
        sendMessage("HelloVolunteer", telegramBot, update, null);

        // Вывод списка пользователей, зовущих волонтера, если таковые есть
        if (botService.getUsersCallingVolunteer() != null) {
            sendMessage("CallingUsers", telegramBot, update, Menu.CALLING_USERS);
        } else {
            sendMessage("NotCallingUsers", telegramBot, update, null);
        }

        // Вывод списка усыновителей, зовущих волонтера, если таковые есть
        if (botService.getAdoptersCallingVolunteer() != null) {
            sendMessage("CallingAdopters", telegramBot, update, Menu.CALLING_ADOPTERS);
        } else {
            sendMessage("NotCallingAdopters", telegramBot, update, null);
        }

        // Вывод списка усыновителей, чьи отчёты нужно проверить сегодня, если таковые есть
        if (botService.getAdoptersReportCheck() != null) {
            sendMessage("AdoptersReportCheck", telegramBot, update, Menu.CHECK_REPORTS);
        } else {
            sendMessage("NotAdoptersReportCheck", telegramBot, update, null);
        }

        // Вывод списка пользователей, желающих стать усыновителями, если таковые есть
        if (botService.getUsersBecomeAdoptive() != null) {
            sendMessage("UsersBecomeAdoptive", telegramBot, update, Menu.USERS_BECOME_ADOPTIVE);
        } else {
            sendMessage("NotUsersBecomeAdoptive", telegramBot, update, null);
        }
    }

    private void adopterUpdateHandle(TelegramBot telegramBot, Update update) {
        // если усыновитель, то проверка когда был отчет, сколько времени. если нужен отчет, то просим, если рано то рано говорим, и выдаем в обоих случаях меню усыновителя
        // если усыновитель и пришла дата окончания проверки и нет продления, а также нарушений, то поздравление иначе отказ и возврат животного
        sendMessage("HelloAdopter", telegramBot, update, null);
        String messageText;
        long adopterId = adopterService.getAdopterByTelegramId(update.message().from().id()).getId();
        // Если список отчетов усыновителя не пуст
        if (reportService.getReportsByAdopter(adopterId) != null) {
            if (reportService.getReportsByAdopterAndReviewed(adopterId, false) != null) {
                String localDateTimes = reportService.getReportsByAdopterAndReviewed(adopterId, false)
                        .stream()
                        .map(Report::getReportDate)
                        .toList()
                        .toString();

                messageText = infoService.getMessage("ReportIsReviewedFalse");
                messageText += " Непроверенные отчеты за даты: " + localDateTimes;
                botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
            } else {
                if (reportService.getReportsByAdopterAndReviewed(adopterId, true) != null) {
                    LocalDateTime reportDateTime = reportService.getReportsByAdopterAndReviewed(adopterId, true)
                            .stream()
                            .map(Report::getReportDate)
                            .max(LocalDateTime::compareTo)
                            .orElse(null);
                    if (reportDateTime != null
                            && reportDateTime.getDayOfMonth() == LocalDate.now().getDayOfMonth()
                            && reportDateTime.isBefore(LocalDate.now().atTime(21, 1))) {
                        messageText = infoService.getMessage("ReportsIsReviewedTrue");
                        botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                    }
                } else {
                    messageText = infoService.getMessage("ReportNotSend");
                    botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.ADOPTER_SEND_REPORT);
                }
            }
        } else {
            messageText = infoService.getMessage("ReportNotSend");
            botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.ADOPTER_SEND_REPORT);
        }
    }

    private void userUpdateHandle(TelegramBot telegramBot, Update update) {
        // если юзер, то приветствие и проверка в бд связывались ли с ним. если в бд не связывались, то вопрос к юзеру связывались?
        if (userService.getUserByTelegramId(update.message().from().id()).isContacted()) {
            sendMessage("Start", telegramBot, update, Menu.START);
        } else {
            sendMessage("StartNotContacted", telegramBot, update, Menu.ANSWER_CONTACTED);
        }
    }

    private void sendMessage(String key_word, TelegramBot telegramBot, Update update, Menu menu) {
        String messageText = infoService.getMessage(key_word);
        botService.sendResponseFromUpdate(telegramBot, update, messageText, menu);
    }
}
