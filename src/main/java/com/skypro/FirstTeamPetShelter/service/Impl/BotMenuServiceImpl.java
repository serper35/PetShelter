package com.skypro.FirstTeamPetShelter.service.Impl;

import com.pengrad.telegrambot.model.request.*;
import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.repository.ShelterRepository;
import com.skypro.FirstTeamPetShelter.service.BotMenuService;
import com.skypro.FirstTeamPetShelter.service.helper.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotMenuServiceImpl implements BotMenuService {
    private final ShelterRepository shelterRepository;

    public BotMenuServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public InlineKeyboardMarkup getStartMenu() {
        List<Shelter> shelters = shelterRepository.findAll();
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[shelters.size()];
        for (int i = 0; i < inlineKeyboardButtons.length; i++) {
            inlineKeyboardButtons[i] = new InlineKeyboardButton(
                    shelters.get(i).getShelterName())
                    .callbackData("/shelter_" + shelters.get(i).getId()
                    );
        }
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    @Override
    public InlineKeyboardMarkup getAnswerContactedMenu() {
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[2];
        inlineKeyboardButtons[0] = new InlineKeyboardButton("Не связывались").callbackData("/not_contacted");
        inlineKeyboardButtons[1] = new InlineKeyboardButton("Связались").callbackData("/contacted");
        return new InlineKeyboardMarkup(inlineKeyboardButtons);
    }

    @Override
    public InlineKeyboardMarkup getShelterMenu() {
        return null;
    }

    @Override
    public InlineKeyboardMarkup getMenu(Menu menu) {
        switch (menu) {
            case START -> {
                return getStartMenu();
            }
            case ANSWER_CONTACTED -> {
                return getAnswerContactedMenu();
            }
        }
        return null;
    }
}
