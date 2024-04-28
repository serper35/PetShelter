package com.skypro.FirstTeamPetShelter.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.skypro.FirstTeamPetShelter.service.helper.Menu;

public interface BotMenuService {
    InlineKeyboardMarkup getStartMenu();
    InlineKeyboardMarkup getAnswerContactedMenu();
    InlineKeyboardMarkup getShelterMenu();

    InlineKeyboardMarkup getMenu(Menu menu);
}
