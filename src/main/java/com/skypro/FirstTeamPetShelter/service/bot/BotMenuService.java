package com.skypro.FirstTeamPetShelter.service.bot;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.skypro.FirstTeamPetShelter.enums.Menu;

public interface BotMenuService {
    InlineKeyboardMarkup getMenu(Menu menu);
}
