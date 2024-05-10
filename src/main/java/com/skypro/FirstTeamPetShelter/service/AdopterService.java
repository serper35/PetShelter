package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.Adopter;

import java.util.Collection;

/**
 * Сервис усыновителя
 */
public interface AdopterService {
    /**
     * Добавить усыновителя в БД
     * @param adopter   - усыновитель
     * @return          - Adopter
     */
    Adopter addAdopter(Adopter adopter);

    /**
     * Получить всех усыновителей
     * @return          - Collection<Adopter>
     */
    Collection<Adopter> getAllAdopters();

    /**
     * Получить усыновителя по ID
     * @param id        - ID усыновителя
     * @return          - Adopter
     */
    Adopter getAdopter(long id);

    /**
     * Редактировать усыновителя
     * @param adopter   - усыновитель
     * @return          - Adopter
     */
    Adopter editAdopter(Adopter adopter);

    /**
     * Удаление усыновителя из БД
     * @param id        - ID усыновителя
     */
    void deleteAdopter(long id);

    /**
     * Получение усыновителя по его ID в телеграм
     * @param telegramId    - ID в телеграм
     * @return              - Adopter
     */
    Adopter getAdopterByTelegramId(long telegramId);
}
