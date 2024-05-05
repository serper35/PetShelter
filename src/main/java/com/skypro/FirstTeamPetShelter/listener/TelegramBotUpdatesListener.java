package com.skypro.FirstTeamPetShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.service.*;
import com.skypro.FirstTeamPetShelter.service.bot.BotHandler;
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

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private BotHandler botHandler;

    @Autowired
    private BotMenuService botMenuService;

    @Autowired
    private BotService botService;

//    @Autowired
//    private InfoService infoService;
//
    @Autowired
    private UserService userService;
//
//    @Autowired
//    private ShelterService shelterService;
//
//    @Autowired
//    private ReportService reportService;
//
//    @Autowired
//    private AdopterService adopterService;

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
                botHandler.updateHandle(telegramBot, update);
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
