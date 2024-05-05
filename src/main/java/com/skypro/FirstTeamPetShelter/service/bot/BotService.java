package com.skypro.FirstTeamPetShelter.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.enums.Menu;
import com.skypro.FirstTeamPetShelter.enums.Role;

import java.util.List;

public interface BotService {
    /**
     * Возвращает роль посетителя бота по ID в Telegram
     * @param visitorId     - ID посетителя
     * @return              - Возвращает Role
     */
    Role getVisitorRole(Long visitorId);
    void sendResponseFromUpdate(TelegramBot telegramBot, Update update, String messageText, Menu menu);
    void sendResponseFromCallback(TelegramBot telegramBot, CallbackQuery callbackQuery, String messageText, Menu menu);
    List<UserApp> getUsersCallingVolunteer();
    List<Adopter> getAdoptersCallingVolunteer();
    List<Report> getAdoptersReportCheck();
    List<UserApp> getUsersBecomeAdoptive();

    void callVolunteer(TelegramBot telegramBot, Update update);
}
