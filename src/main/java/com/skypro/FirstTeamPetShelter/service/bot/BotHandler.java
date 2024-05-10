package com.skypro.FirstTeamPetShelter.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;

/**
 * Обработчик команд от бота
 */
public interface BotHandler {
    /**
     * Обработка Update
     * @param telegramBot   - com.pengrad.telegrambot.TelegramBot
     * @param update        - com.pengrad.telegrambot.model.Update
     */
    void updateHandle(TelegramBot telegramBot, Update update);

    /**
     * Обработка CallbackQuery
     * @param telegramBot   - com.pengrad.telegrambot.TelegramBot
     * @param callbackQuery - com.pengrad.telegrambot.model.CallbackQuery
     */
    void callbackHandle(TelegramBot telegramBot, CallbackQuery callbackQuery);
}
