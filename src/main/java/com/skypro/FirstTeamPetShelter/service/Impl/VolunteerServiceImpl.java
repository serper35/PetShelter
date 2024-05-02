package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.VolunteerNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Volunteer;
import com.skypro.FirstTeamPetShelter.repository.VolunteerRepository;
import com.skypro.FirstTeamPetShelter.service.VolunteerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final Logger logger = LoggerFactory.getLogger(VolunteerServiceImpl.class);

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer addVolunteer(Volunteer volunteer) {
        logger.info("Сохранение волонтёра с ID {}", volunteer.getId());
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id).orElseThrow(() -> {
            logger.error("Волонтёр с ID {} не найден!", id);
            return new VolunteerNotFoundException("Волонтёр с ID " + id + " не найден!");
        });
    }

    @Override
    public Volunteer getVolunteerByTelegramId(Long telegramID) {
        return volunteerRepository.findByVolunteerTelegramId(telegramID);
    }

    @Override
    public Collection<Volunteer> getAllVolunteer() {
        return volunteerRepository.findAll();
    }

    @Override
    public void deleteVolunteer(Long id) {
        logger.warn("Внимание! Удаляется волонтёр с ID {}", id);
        volunteerRepository.deleteById(id);
    }

    @Override
    public Volunteer editVolunteer(Volunteer volunteer) {
        logger.warn("Изменение волонтёра с ID {}", volunteer.getId());
        return volunteerRepository.save(volunteer);
    }
}
