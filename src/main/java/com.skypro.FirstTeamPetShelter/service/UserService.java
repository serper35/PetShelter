package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.User;

import java.util.Collection;

/**
 * Сервис пользователя
 */
public interface UserService {
    /**
     * Добавить пользователя в БД
     * @param user  - Пользователь (User)
     * @return      - Возвращает User
     * @throws com.skypro.FirstTeamPetShelter.exception.UserAlreadyAddedException, если такой User уже есть в БД
     */
    User addUser(User user);

    /**
     * Получить пользователя из БД
     * @param id    - ID пользователя
     * @return      - Возвращает User
     * @throws com.skypro.FirstTeamPetShelter.exception.UserNotFoundException, если User не найден
     */
    User getUser(long id);

    /**
     * Получить всех пользователей из БД
     * @return      - Возвращает Collection<User>
     */
    Collection<User> getAllUser();

    /**
     * Отредактировать пользователя
     * @param id    - ID пользователя
     * @return      - Возвращает User
     * @throws com.skypro.FirstTeamPetShelter.exception.UserNotFoundException, если User не найден
     */
    User editUser(User user);

    /**
     * Удалить пользователя
     * @param id    - ID пользователя
     * @throws com.skypro.FirstTeamPetShelter.exception.UserNotFoundException, если User не найден
     */
    void deleteUser(long id);
}
