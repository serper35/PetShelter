package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.model.ShelterImage;
import com.skypro.FirstTeamPetShelter.repository.ShelterImageRepository;
import com.skypro.FirstTeamPetShelter.service.ShelterImageService;
import com.skypro.FirstTeamPetShelter.service.ShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class ShelterImageServiceImpl implements ShelterImageService {
    private final ShelterImageRepository shelterImageRepository;
    private final Logger logger = LoggerFactory.getLogger(ShelterImageServiceImpl.class);
    private final ShelterService shelterService;

    public ShelterImageServiceImpl(ShelterImageRepository shelterImageRepository, ShelterService shelterService) {
        this.shelterImageRepository = shelterImageRepository;
        this.shelterService = shelterService;
    }

    @Override
    public void addShelterAvatarAndDrivingDirection(Long shelterId, MultipartFile shelterAvatar, MultipartFile shelterDrivingDirection) throws IOException {
        logger.info("Загрузка изображений приюта в систему");
        Shelter shelter = shelterService.getShelter(shelterId);
        if (shelter != null) {
            ShelterImage shelterImage = new ShelterImage();
            shelterImage.setShelter(shelter);
            shelterImage.setShelterAvatar(shelterAvatar.getBytes());
            shelterImage.setShelterDrivingDirection(shelterDrivingDirection.getBytes());
            shelterImageRepository.save(shelterImage);
        } else {
            logger.warn("Приют с ID {} не найден! Изображения не будут добавлены.", shelterId);
        }
    }

    @Override
    public ShelterImage getShelterImageById(long id) {
        return shelterImageRepository.findById(id).orElse(null);
    }

    @Override
    public ShelterImage getShelterImageByShelterId(long shelterId) {
        return shelterImageRepository.findByShelterId(shelterId);
    }

    @Override
    public ShelterImage editShelterImage(long id, MultipartFile shelterAvatar, MultipartFile shelterDrivingDirection) throws IOException {
        logger.warn("Редактирование изображений приюта!");
        ShelterImage shelterImage = getShelterImageById(id);
        if (shelterAvatar != null) {
            shelterImage.setShelterAvatar(shelterAvatar.getBytes());
        }
        if (shelterDrivingDirection != null) {
            shelterImage.setShelterDrivingDirection(shelterDrivingDirection.getBytes());
        }
        return shelterImageRepository.save(shelterImage);
    }

    @Override
    public void deleteShelterImage(long id) {
        logger.warn("Удаление изображений приюта (аватар и схема проезда)!");
        shelterImageRepository.deleteById(id);
    }
}
