package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.Shelter;

import java.util.Collection;

/**
 * Сервис приюта
 */
public interface ShelterService {
    /**
     * Добавить приют в БД
     * @param shelter   - Приют
     * @return          - Возвращает Shelter
     */
    Shelter addShelter(Shelter shelter);

    /**
     * Получить приют
     * @param id        - ID приюта
     * @return          - Возвращает Shelter
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterNotFoundException, если приют не найден
     */
    Shelter getShelter(long id);

    /**
     * Получить список всех приютов
     * @return          - Возвращает Collection<Shelter>
     */
    Collection<Shelter> getAllShelters();

    /**
     * Отредактировать приют
     * @param id        - ID приюта
     * @return          - Возвращает Shelter
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterNotFoundException, если приют не найден
     */
    Shelter editShelter(long id);

    /**
     * Удалить приют
     * @param id        - ID приюта
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterNotFoundException, если приют не найден
     */
    void deleteShelter(long id);
}
