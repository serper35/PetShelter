package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.AdopterNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.repository.AdopterRepository;
import com.skypro.FirstTeamPetShelter.service.AdopterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdopterServiceImpl implements AdopterService {
    final AdopterRepository adopterRepository;
    final Logger logger = LoggerFactory.getLogger(AdopterServiceImpl.class);

    public AdopterServiceImpl(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    @Override
    public Adopter addAdopter(Adopter adopter) {
        logger.info("Сохранение усыновителя с ID {}", adopter.getId());
        return adopterRepository.save(adopter);
    }

    @Override
    public Collection<Adopter> getAllAdopters() {
        return adopterRepository.findAll();
    }

    @Override
    public Adopter getAdopter(long id) {
        return adopterRepository.findById(id).orElseThrow(() -> {
            logger.error("Усыновитель с ID {} не найден!", id);
            return new AdopterNotFoundException("Усыновитель с ID " + id + " не найден!");
        });
    }

    @Override
    public Adopter editAdopter(Adopter adopter) {
        return adopterRepository.findById(adopter.getId()).map(
                editableAdopter -> {
                    editableAdopter.setAdopterName(adopter.getAdopterName());
                    editableAdopter.setAdopterReports(adopter.getAdopterReports());
                    editableAdopter.setAdopterTelegramId(adopter.getAdopterTelegramId());
                    editableAdopter.setAdopterPhoneNumber(adopter.getAdopterPhoneNumber());
                    return adopterRepository.save(editableAdopter);
                }).orElse(null);
    }

    @Override
    public void deleteAdopter(long id) {
        logger.warn("Удаляется усыновитель из БД с ID {}", id);
        adopterRepository.deleteById(id);
    }

    @Override
    public Adopter getAdopterByTelegramId(long telegramId) {
        return adopterRepository.findByAdopterTelegramId(telegramId);
    }
}
