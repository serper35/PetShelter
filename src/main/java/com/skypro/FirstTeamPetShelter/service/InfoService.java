package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.Info;

import java.util.Collection;

/**
 * Информационный сервис
 */
public interface InfoService {
    /**
     * Добавить сообщение в БД
     * @param info      - Информация
     * @return          - Возвращается Info
     */
    Info addInfo(Info info);

    /**
     * Получить сообщение из БД по индексу
     * @param id        - ID сообщения
     * @return          - Возвращается строка сообщения (String)
     * @throws com.skypro.FirstTeamPetShelter.exception.InfoMessageNotFoundException, если сообщение не найдено
     */
    String getMessage(long id);

    /**
     * Получить сообщение из БД по ключевому слову
     * @param keyWord   - Ключевое слово
     * @return          - Возвращается строка сообщения (String)
     * @throws com.skypro.FirstTeamPetShelter.exception.InfoMessageNotFoundException, если сообщение не найдено
     */
    String getMessage(String keyWord);

    /**
     * Получить все информационные сообщения из БД
     * @return          - Возвращается Collection<Info>
     */
    Collection<Info> getAllInfo();

    /**
     * Удалить информацию из БД по индексу
     * @param id        - ID информации
     * @throws com.skypro.FirstTeamPetShelter.exception.InfoMessageNotFoundException, если сообщение не найдено
     */
    void deleteInfo(long id);

    /**
     * Удалить информацию из БД по ключевому слову
     * @param keyWord   - Ключевое слово информации
     * @throws com.skypro.FirstTeamPetShelter.exception.InfoMessageNotFoundException, если сообщение не найдено
     */
    void deleteInfo(String keyWord);
}
