package com.skypro.FirstTeamPetShelter.service.bot;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.skypro.FirstTeamPetShelter.service.bot.helper.Menu;

public interface BotMenuService {
    InlineKeyboardMarkup getStartMenu();
    InlineKeyboardMarkup getAnswerContactedMenu();
    InlineKeyboardMarkup getShelterMenu();

    InlineKeyboardMarkup getMenu(Menu menu);
}
