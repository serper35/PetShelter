package com.skypro.FirstTeamPetShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.skypro.FirstTeamPetShelter.service.bot.BotHandler;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private BotHandler botHandler;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Обработка обновления {}", update);
            CallbackQuery callbackQuery = update.callbackQuery();
            if (callbackQuery != null) {
                botHandler.callbackHandle(telegramBot, callbackQuery);
            }
            if (update.message() != null) {
                botHandler.updateHandle(telegramBot, update);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
