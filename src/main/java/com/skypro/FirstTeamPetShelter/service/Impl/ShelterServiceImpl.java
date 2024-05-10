package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.ShelterNotFoundException;
import com.skypro.FirstTeamPetShelter.exception.UserNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.repository.ShelterRepository;
import com.skypro.FirstTeamPetShelter.service.ShelterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShelterServiceImpl implements ShelterService {
    private ShelterRepository shelterRepository;

    private Logger logger = LoggerFactory.getLogger(ShelterServiceImpl.class);

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Shelter addShelter(Shelter shelter) {
        logger.info("Method addShelter was invoked");

        Shelter savedShelter = shelterRepository.save(shelter);

        logger.info("Shelter added: {}", savedShelter);
        return savedShelter;
    }

    @Override
    public Shelter getShelter(long id) {
        logger.info("Method getShelter was invoked");

        Shelter shelter = shelterRepository.findById(id).orElse(null);

        if (shelter == null) {
            throw new ShelterNotFoundException("Приют не найден");
        }

        logger.info("Shelter found");
        return shelter;
    }

    @Override
    public Collection<Shelter> getAllShelters() {
        logger.info("Method getAllShelters was invoked");

        return shelterRepository.findAll();
    }

    @Override
    public Shelter editShelter(Shelter shelter) {
        logger.info("Method editShelter was invoked");

        Shelter shelterToEdit = shelterRepository.findById(shelter.getId()).orElse(null);

        if (shelterToEdit == null) {
            throw new ShelterNotFoundException("Приют не найден");
        }

        Shelter updatedShelter = shelterRepository.save(shelter);

        logger.info("Shelter edited: {}", updatedShelter);
        return updatedShelter;
    }

    @Override
    public void deleteShelter(long id) {
        logger.info("Method editShelter was invoked");

        Shelter shelter = shelterRepository.findById(id).orElse(null);

        if (shelter == null) {
            throw new ShelterNotFoundException("Приют не найден");
        }

        shelterRepository.deleteById(id);
        logger.info("Shelter deleted");
    }
}
