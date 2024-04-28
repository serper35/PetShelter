package com.skypro.FirstTeamPetShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.skypro.FirstTeamPetShelter.service.*;
import com.skypro.FirstTeamPetShelter.service.helper.Menu;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                            try {
                                String messageText = infoService.getMessage("Start");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.START);
                            } catch (RuntimeException e) {
                                logger.error(e.getMessage());
                                String messageText = infoService.getMessage("StartError");
                                botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                            }
                        }
                        case USER -> {
                            // если юзер, то приветствие и проверка в бд связывались ли с ним. если в бд не связывались, то вопрос к юзеру связывались?
                            if (userService.getUserByTelegramId(update.message().from().id()).isContacted()) {
                                try {
                                    String messageText = infoService.getMessage("Start");
                                    botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.START);
                                } catch (RuntimeException e) {
                                    logger.error(e.getMessage());
                                    String messageText = infoService.getMessage("StartError");
                                    botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                                }
                            } else {
                                try {
                                    String messageText = infoService.getMessage("StartNotContacted");
                                    botService.sendResponseFromUpdate(telegramBot, update, messageText, Menu.ANSWER_CONTACTED);
                                } catch (RuntimeException e) {
                                    logger.error(e.getMessage());
                                    String messageText = infoService.getMessage("StartError");
                                    botService.sendResponseFromUpdate(telegramBot, update, messageText, null);
                                }
                                String messageText = "Здравствуйте! С вами ещё не связывались?";
                                SendMessage sendMessage = new SendMessage(update.message().chat().id(), messageText);
                                sendMessage.replyMarkup(botMenuService.getAnswerContactedMenu());
                                SendResponse sendResponse = telegramBot.execute(sendMessage);
                            }
                        }
                        case ADOPTER -> {
                            // если усыновитель, то проверка когда был отчет, сколько времени. если нужен отчет, то просим, если рано то рано говорим, и выдаем в обоих случаях меню усыновителя
                            // если усыновитель и пришла дата окончания проверки и нет продления, а также нарушений, то поздравление иначе отказ и возврат животного

                        }
                        case VOLUNTEER -> {
                            // если волонтер, то ищем кто хочет связаться и выдаем список кнопок связаться + Кнопка посмотреть отчеты
                            String helloVolunteer = infoService.getMessage("Здравствуйте. Хотите поработать сегодня!?");
                            SendMessage sendMessage = new SendMessage(update.message().chat().id(), helloVolunteer);
                            SendResponse sendResponse = telegramBot.execute(sendMessage);

                            if (botService.getUsersCallingVolunteer() != null) {
                                String messageText = "Вот список новых пользователей, которые хотят связаться с волонтёром\n" +
                                        "Выберите пользователя, с которым готовы поговорить.";
                            } else {
                                String messageText = "Пользователей, которым нужна помощь нет.";
                            }
                            if (botService.getAdoptersCallingVolunteer() != null) {
                                String messageText = "Вот список усыновителей, которые хотят связаться с волонтёром\n" +
                                        "Выберите пользователя, с которым готовы поговорить.";
                            } else {
                                String messageText = "Усыновителей, которым нужна помощь нет!";
                            }
                            if (botService.getAdoptersReportCheck() != null) {
                                String messageText = "Список усыновителей, чьи отчёты нужно проверить.";
                            }
                            String messageText = "Здравствуйте! ";
                        }
                    }
                }
                if (update.message().text().contains("/shelter_")) {
                    shelter_id = Long.parseLong(update.message().text().replace("/shelter_", ""));
                    String messageText = "Здравствуйте! Спасибо, что выбрали наш приют" + shelterService.getShelter(shelter_id).getShelterName();
                    SendMessage sendMessage = new SendMessage(update.message().chat().id(), messageText);
                    sendMessage.replyMarkup(botMenuService.getShelterMenu());
                    SendResponse sendResponse = telegramBot.execute(sendMessage);
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
