package com.skypro.FirstTeamPetShelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.service.helper.Menu;
import com.skypro.FirstTeamPetShelter.service.helper.Role;

import java.util.List;

public interface BotService {
    /**
     * Возвращает роль посетителя бота по ID в Telegram
     * @param visitorId     - ID посетителя
     * @return              - Возвращает Role
     */
    Role getVisitorRole(Long visitorId);
    void sendResponseFromUpdate(TelegramBot telegramBot, Update update, String messageText, Menu menu);
    List<UserApp> getUsersCallingVolunteer();
    List<Adopter> getAdoptersCallingVolunteer();
    List<Adopter> getAdoptersReportCheck();
    List<UserApp> getUsersBecomeAdoptive();
}
