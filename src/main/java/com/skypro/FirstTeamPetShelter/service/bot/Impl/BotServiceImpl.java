package com.skypro.FirstTeamPetShelter.service.bot.Impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.repository.AdopterRepository;
import com.skypro.FirstTeamPetShelter.repository.UserRepository;
import com.skypro.FirstTeamPetShelter.repository.VolunteerRepository;
import com.skypro.FirstTeamPetShelter.service.bot.BotMenuService;
import com.skypro.FirstTeamPetShelter.service.bot.BotService;
import com.skypro.FirstTeamPetShelter.service.InfoService;
import com.skypro.FirstTeamPetShelter.service.bot.helper.Menu;
import com.skypro.FirstTeamPetShelter.service.bot.helper.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotServiceImpl implements BotService {
    private final Logger logger = LoggerFactory.getLogger(BotService.class);

    private final UserRepository userRepository;
    private final AdopterRepository adopterRepository;
    private final VolunteerRepository volunteerRepository;

    private final BotMenuService botMenuService;
    private final InfoService infoService;

    public BotServiceImpl(UserRepository userRepository, AdopterRepository adopterRepository, VolunteerRepository volunteerRepository, BotMenuService botMenuService, InfoService infoService) {
        this.userRepository = userRepository;
        this.adopterRepository = adopterRepository;
        this.volunteerRepository = volunteerRepository;
        this.botMenuService = botMenuService;
        this.infoService = infoService;
    }

    @Override
    public Role getVisitorRole(Long visitorId) {
        if (userRepository.findByUserTelegramId(visitorId).getId() != null) {
            return Role.USER;
        } else if (adopterRepository.findByAdopterTelegramId(visitorId).getId() != null) {
            return Role.ADOPTER;
        } else if (volunteerRepository.findByVolunteerTelegramId(visitorId).getId() != null) {
            return Role.VOLUNTEER;
        } else {
            return Role.NEWBIE;
        }
    }

    @Override
    public void sendResponseFromUpdate(TelegramBot telegramBot, Update update, String messageText, Menu menu) {
        messageText = parseMessageText(update, messageText);
        SendMessage sendMessage;
        try {
            sendMessage = new SendMessage(update.message().chat().id(), messageText);
            if (menu != null) {
                sendMessage.replyMarkup(botMenuService.getMenu(menu));
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            messageText = infoService.getMessage("Error");
            sendMessage = new SendMessage(update.message().chat().id(), messageText);
        }
        telegramBot.execute(sendMessage);
    }

    @Override
    public List<UserApp> getUsersCallingVolunteer() {
        return List.of();
    }

    @Override
    public List<Adopter> getAdoptersCallingVolunteer() {
        return List.of();
    }

    @Override
    public List<Adopter> getAdoptersReportCheck() {
        return List.of();
    }

    @Override
    public List<UserApp> getUsersBecomeAdoptive() {
        return List.of();
    }

    private String parseMessageText(Update update, String messageText) {
        String result = messageText;
        if (result.contains("{username}")) {
            result = result.replace("{username}", update.message().from().firstName());
        }
        if (result.contains("{usercontact}")) {
            result = result.replace("{usercontact}", userRepository.findByUserTelegramId(update.message().from().id()).getUserPhoneNumber());
        }
        return result;
    }
}
