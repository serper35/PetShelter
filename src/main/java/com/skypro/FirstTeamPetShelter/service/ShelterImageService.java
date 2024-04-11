package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.ShelterImage;

/**
 * Сервис изображений приюта
 */
public interface ShelterImageService {
    /**
     * Добавление изображений приюта (аватар приюта и схема проезда приюта)
     * @param shelterImage  - Изображения приюта
     * @return              - Возвращает ShelterImage
     */
    ShelterImage addShelterImage(ShelterImage shelterImage);

    /**
     * Получить изображения приюта
     * @param id            - ID изображений приюта
     * @return              - Возвращает ShelterImage
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterImageNotFoundException, если изображение приюта не найдено
     */
    ShelterImage getShelterImage(long id);

    /**
     * Отредактировать изображения приюта
     * @param id            - ID изображений приюта
     * @return              - Возвращает ShelterImage
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterImageNotFoundException, если изображение приюта не найдено
     */
    ShelterImage editShelterImage(long id);

    /**
     * Удаление изображений приюта
     * @param id            - ID изображений приюта
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterImageNotFoundException, если изображение приюта не найдено
     */
    void deleteShelterImage(long id);
}