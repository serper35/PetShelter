package com.skypro.FirstTeamPetShelter.service;

/**
 * Информационный сервис
 */
public interface InfoService {
    /**
     * Получить сообщение из БД
     * @param id    - ID сообщения
     * @return      - Возвращается строка сообщения (String)
     * @throws com.skypro.FirstTeamPetShelter.exception.InfoMessageNotFoundException, если сообщение не найдено
     */
    String getMessage(long id);
}
