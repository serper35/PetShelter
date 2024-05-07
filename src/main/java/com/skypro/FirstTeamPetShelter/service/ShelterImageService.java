package com.skypro.FirstTeamPetShelter.service;

import com.skypro.FirstTeamPetShelter.model.ShelterImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Сервис изображений приюта
 */
public interface ShelterImageService {
    /**
     * Добавление изображений приюта (аватар приюта и схема проезда приюта)
     *
     * @param shelterId                 - ID приюта
     * @param shelterAvatar             - Основное изображение приюта
     * @param shelterDrivingDirection   - Схема проезда
     */
    void addShelterAvatarAndDrivingDirection(Long shelterId, MultipartFile shelterAvatar, MultipartFile shelterDrivingDirection) throws IOException;

    /**
     * Получить изображения приюта
     * @param id            - ID изображений приюта
     * @return              - Возвращает ShelterImage
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterImageNotFoundException, если изображение приюта не найдено
     */
    ShelterImage getShelterImageById(long id);

    ShelterImage getShelterImageByShelterId(long shelterId);

    /**
     * Отредактировать изображения приюта
     *
     * @param id                      - ID изображений приюта
     * @param shelterAvatar           - Аватар приюта
     * @param shelterDrivingDirection - Схема проезда
     * @return - Возвращает ShelterImage
     * @throws com.skypro.FirstTeamPetShelter.exception.ShelterImageNotFoundException, если изображение приюта не найдено
     */
    ShelterImage editShelterImage(long id, MultipartFile shelterAvatar, MultipartFile shelterDrivingDirection) throws IOException;

    /**
     * Удаление изображений приюта
     * @param id            - ID изображений приюта
     */
    void deleteShelterImage(long id);
}