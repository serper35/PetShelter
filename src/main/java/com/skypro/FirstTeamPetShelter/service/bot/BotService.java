package com.skypro.FirstTeamPetShelter.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.skypro.FirstTeamPetShelter.model.Pet;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.enums.Menu;
import com.skypro.FirstTeamPetShelter.enums.Role;

/**
 * Сервис бота
 */
public interface BotService {
    /**
     * Возвращает роль посетителя бота по ID в Telegram
     * @param visitorId     - ID посетителя
     * @return              - Возвращает Role
     */
    Role getVisitorRole(Long visitorId);

    /**
     * Отправка сообщения из Update
     * @param telegramBot       - TelegramBot
     * @param update            - Update
     * @param messageText       - Сообщение
     * @param menu              - Меню
     */
    void sendResponseFromUpdate(TelegramBot telegramBot, Update update, String messageText, Menu menu);

    /**
     * Отправка сообщения из Callback
     * @param telegramBot       - TelegramBot
     * @param callbackQuery     - CallbackQuery
     * @param messageText       - Сообщение
     * @param menu              - Меню
     * @param shelter           - Приют
     */
    void sendResponseFromCallback(TelegramBot telegramBot, CallbackQuery callbackQuery, String messageText, Menu menu, Shelter shelter);

    /**
     * Отправить текстовое сообщение
     * @param telegramBot       - TelegramBot
     * @param id                - ID чата
     * @param messageText       - Сообщение
     * @param menu              - Меню
     */
    void executeMessage(TelegramBot telegramBot, Long id, String messageText, Menu menu);

    /**
     * Отправить сообщение с изображением
     * @param caption           - Подпись под изображением
     * @param telegramBot       - TelegramBot
     * @param callbackQuery     - CallbackQuery
     * @param menu              - Меню
     * @param Image             - Изображение (массив byte[])
     * @param shelter           - Приют
     */
    void executeImageMessage(String caption, TelegramBot telegramBot, CallbackQuery callbackQuery, Menu menu, byte[] Image, Shelter shelter);

    /**
     * Позвать волонтёра
     * @param telegramBot       - TelegramBot
     * @param update            - Update
     */
    void callVolunteer(TelegramBot telegramBot, Update update);

    /**
     * Потенциальный усыновитель
     * @param pet               - Pet
     * @param telegramBot       - TelegramBot
     * @param callbackQuery     - CallbackQuery
     */
    void setAdoptiveParent(Pet pet, TelegramBot telegramBot, CallbackQuery callbackQuery);

    /**
     * Получить список питомцев
     * @param telegramBot       - TelegramBot
     * @param callbackQuery     - CallbackQuery
     * @param shelter           - приют
     */
    void getPets(TelegramBot telegramBot, CallbackQuery callbackQuery, Shelter shelter);

    /**
     * Установка номера телефона пользователю
     * @param phone             - Номер телефона
     * @param telegramBot       - TelegramBot
     * @param update            - Update
     */
    void setUserPhone(String phone, TelegramBot telegramBot, Update update);

    /**
     * Получение отчёта от усыновителя
     *
     * @param telegramBot - TelegramBot
     * @param chatId      - CallbackQuery
     * @return - Report
     */
    Report reportFromAdopterStart(TelegramBot telegramBot, long chatId);

    /**
     * Одобрено ли усыновление
     * @param telegramBot           - TelegramBot
     * @param id                    - ID
     * @param userBecomeAdoptiveId  - Telegram ID потенциального усыновителя
     */
    void adoptive(TelegramBot telegramBot, Long id, long userBecomeAdoptiveId);

    void reportImage(String m, TelegramBot telegramBot, Long id, byte[] petPhoto, Report r);

    void reportMessage(TelegramBot telegramBot, Long id, String m, Report r);
}
