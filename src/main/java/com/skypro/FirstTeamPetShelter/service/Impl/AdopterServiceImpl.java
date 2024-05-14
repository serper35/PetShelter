package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.AdopterNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.repository.AdopterRepository;
import com.skypro.FirstTeamPetShelter.service.AdopterService;
import com.skypro.FirstTeamPetShelter.service.PetService;
import com.skypro.FirstTeamPetShelter.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdopterServiceImpl implements AdopterService {
    final AdopterRepository adopterRepository;
    final Logger logger = LoggerFactory.getLogger(AdopterServiceImpl.class);

    final ReportService reportService;
    final PetService petService;

    public AdopterServiceImpl(AdopterRepository adopterRepository, ReportService reportService, PetService petService) {
        this.adopterRepository = adopterRepository;
        this.reportService = reportService;
        this.petService = petService;
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
        if (this.getAdopter(id).getAdopterReports() != null) {
            logger.warn("Найдены отчёты! Они также будут удалены.");
            for(Report report: reportService.getReportsByAdopter(id)) {
                reportService.deleteReport(report.getId());
            }
        }
        if (petService.getPetByOwner(id) != null) {
            logger.warn("Усыновителю принадлежал питомец {}. Он также будет удалён из БД.", petService.getPetByOwner(id));
            petService.deletePet(petService.getPetByOwner(id).getId());
        }
        adopterRepository.deleteById(id);
    }

    @Override
    public Adopter getAdopterByTelegramId(long telegramId) {
        return adopterRepository.findByAdopterTelegramId(telegramId);
    }
}
