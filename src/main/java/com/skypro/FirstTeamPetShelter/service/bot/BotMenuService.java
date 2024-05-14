package com.skypro.FirstTeamPetShelter.service.bot;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.skypro.FirstTeamPetShelter.enums.Menu;

/**
 * Сервис создания меню
 */
public interface BotMenuService {
    /**
     * Получить меню
     * @param menu  - Menu
     * @return      - InlineKeyBoardMarkup
     */
    InlineKeyboardMarkup getMenu(Menu menu);
}
