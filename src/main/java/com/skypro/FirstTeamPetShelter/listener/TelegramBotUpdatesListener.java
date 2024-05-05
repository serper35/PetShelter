package com.skypro.FirstTeamPetShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.service.*;
import com.skypro.FirstTeamPetShelter.service.bot.BotMenuService;
import com.skypro.FirstTeamPetShelter.service.bot.BotService;
import com.skypro.FirstTeamPetShelter.service.bot.helper.Menu;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private long shelter_id;

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private BotMenuService botMenuService;

    @Autowired
    private BotService botService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShelterService shelterService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AdopterService adopterService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Обработка обновления {}", update);
            CallbackQuery callbackQuery = update.callbackQuery();
            if (update.message() != null) {
                if (update.message().text().equals("/start")) {
                    // проверка пользователя - кто это - новый, юзер, усыновитель или волонтер
                    // если новый, то приветствие и список приютов
                    switch (botService.getVisitorRole(update.message().from().id())) {
                        case NEWBIE -> {
                            String messageText = infoService.getMessage("Start");
                            botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.START);
                        }
                        case USER -> {
                            // если юзер, то приветствие и проверка в бд связывались ли с ним. если в бд не связывались, то вопрос к юзеру связывались?
                            if (userService.getUserByTelegramId(update.message().from().id()).isContacted()) {
                                String messageText = infoService.getMessage("Start");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.START);
                            } else {
                                String messageText = infoService.getMessage("StartNotContacted");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.ANSWER_CONTACTED);
                            }
                        }
                        case ADOPTER -> {
                            // если усыновитель, то проверка когда был отчет, сколько времени. если нужен отчет, то просим, если рано то рано говорим, и выдаем в обоих случаях меню усыновителя
                            // если усыновитель и пришла дата окончания проверки и нет продления, а также нарушений, то поздравление иначе отказ и возврат животного
                            String messageText = infoService.getMessage("HelloAdopter");
                            botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
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
                        case VOLUNTEER -> {
                            // Приветствие волонтера
                            String helloVolunteer = infoService.getMessage("HelloVolunteer");
                            botService.sendResponseFromUpdate(telegramBot, update, helloVolunteer, null);

                            // Вывод списка пользователей, зовущих волонтера, если таковые есть
                            if (botService.getUsersCallingVolunteer() != null) {
                                String messageText = infoService.getMessage("CallingUsers");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.CALLING_USERS);
                            } else {
                                String messageText = infoService.getMessage("NotCallingUsers");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                            }

                            // Вывод списка усыновителей, зовущих волонтера, если таковые есть
                            if (botService.getAdoptersCallingVolunteer() != null) {
                                String messageText = infoService.getMessage("CallingAdopters");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.CALLING_ADOPTERS);
                            } else {
                                String messageText = infoService.getMessage("NotCallingAdopters");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                            }

                            // Вывод списка усыновителей, чьи отчёты нужно проверить сегодня, если таковые есть
                            if (botService.getAdoptersReportCheck() != null) {
                                String messageText = infoService.getMessage("AdoptersReportCheck");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.CHECK_REPORTS);
                            } else {
                                String messageText = infoService.getMessage("NotAdoptersReportCheck");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                            }

                            // Вывод списка пользователей, желающих стать усыновителями, если таковые есть
                            if (botService.getUsersBecomeAdoptive() != null) {
                                String messageText = infoService.getMessage("UsersBecomeAdoptive");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.USERS_BECOME_ADOPTIVE);
                            } else {
                                String messageText = infoService.getMessage("NotUsersBecomeAdoptive");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                            }
                        }
                    }
                }
                if (update.message().text().equals("/info")) {
                    String messageText = infoService.getMessage("BotInformation");
                    botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                }
                if (update.message().text().equals("/call-volunteer")) {
                    String messageText = infoService.getMessage("CallingVolunteer");
                    botService.callVolunteer(telegramBot, update);
                    botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                }
            }
            if (callbackQuery != null) {
                // если да, то в бд пишем да и выдаем Готовы выбрать приют и животное? если нет, то Свяжемся позже и может вы готовы выбрать и список приютов
                if (callbackQuery.data().equals("/not_contacted")) {
                    String messageText = "С вами обязательно свяжутся в ближайшее время, а пока вы можете выбрать приют.";
                    SendMessage sendMessage = new SendMessage(callbackQuery.from().id(), messageText);
                    sendMessage.replyMarkup(botMenuService.getStartMenu());
                    SendResponse sendResponse = telegramBot.execute(sendMessage);
                } else if (callbackQuery.data().equals("/contacted")) {
                    userService.editUser(userService.getUserByTelegramId(callbackQuery.from().id())).setContacted(true);
                    String messageText = "Надеемся вы решили все вопросы и готовы выбрать приют и питомца.";
                    SendMessage sendMessage = new SendMessage(callbackQuery.from().id(), messageText);
                    sendMessage.replyMarkup(botMenuService.getStartMenu());
                    SendResponse sendResponse = telegramBot.execute(sendMessage);
                } else if (callbackQuery.data().equals("/shelter_info")) {

                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
