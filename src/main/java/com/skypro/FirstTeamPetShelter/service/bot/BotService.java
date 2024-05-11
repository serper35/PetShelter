package com.skypro.FirstTeamPetShelter.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.enums.Menu;
import com.skypro.FirstTeamPetShelter.enums.Role;

public interface BotService {
    /**
     * Возвращает роль посетителя бота по ID в Telegram
     * @param visitorId     - ID посетителя
     * @return              - Возвращает Role
     */
    Role getVisitorRole(Long visitorId);
    void sendResponseFromUpdate(TelegramBot telegramBot, Update update, String messageText, Menu menu);
    void sendResponseFromCallback(TelegramBot telegramBot, CallbackQuery callbackQuery, String messageText, Menu menu, Shelter shelter);
    void executeMessage(TelegramBot telegramBot, Long id, String messageText, Menu menu);
    void executeImageMessage(String caption, TelegramBot telegramBot, CallbackQuery callbackQuery, Menu menu, byte[] Image, Shelter shelter);
    void callVolunteer(TelegramBot telegramBot, Update update);

    void getPets(TelegramBot telegramBot, CallbackQuery callbackQuery, Shelter shelter);
    void setUserPhone(String phone, TelegramBot telegramBot, Update update);
}
