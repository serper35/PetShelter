package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.UserApp;

import java.util.Collection;

/**
 * Сервис пользователя
 */
public interface UserService {
    /**
     * Добавить пользователя в БД
     * @param userApp  - Пользователь (UserApp)
     * @return      - Возвращает UserApp
     * @throws com.skypro.FirstTeamPetShelter.exception.UserAlreadyAddedException, если такой UserApp уже есть в БД
     */
    UserApp addUser(UserApp userApp);

    /**
     * Получить пользователя из БД
     * @param id    - ID пользователя
     * @return      - Возвращает UserApp
     * @throws com.skypro.FirstTeamPetShelter.exception.UserNotFoundException, если UserApp не найден
     */
    UserApp getUser(long id);

    /**
     * Получить всех пользователей из БД
     * @return      - Возвращает Collection<UserApp>
     */
    Collection<UserApp> getAllUser();

    /**
     * Отредактировать пользователя
     * @param userApp    - ID пользователя
     * @return      - Возвращает UserApp
     * @throws com.skypro.FirstTeamPetShelter.exception.UserNotFoundException, если UserApp не найден
     */
    UserApp editUser(UserApp userApp);

    /**
     * Удалить пользователя
     * @param id    - ID пользователя
     * @throws com.skypro.FirstTeamPetShelter.exception.UserNotFoundException, если UserApp не найден
     */
    void deleteUser(long id);

    /**
     * Получить пользователя по Telegram ID
     *
     * @param telegramId - ID пользователя в Telegram
     * @return - Возвращает пользователя
     * @throws com.skypro.FirstTeamPetShelter.exception.UserNotFoundException, если UserApp не найден
     */
    UserApp getUserByTelegramId(long telegramId);
}
