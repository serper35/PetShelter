package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.ReportNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.repository.ReportRepository;
import com.skypro.FirstTeamPetShelter.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class ReportServiceImpl implements ReportService {
    final ReportRepository reportRepository;
    final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report addReport(Report report) {
        logger.info("Сохранение отчёта с ID {}...", report.getId());
        return reportRepository.save(report);
    }

    @Override
    public Report getReportById(long id) {
        return reportRepository.findById(id).orElseThrow(() -> {
            logger.error("Невозможно получить отчёт! Данные Report с ID: {} не найден!", id);
            return new ReportNotFoundException("Невозможно получить отчёт! Данные Report с ID: " + id + " не найден!");
        });
    }

    @Override
    public Collection<Report> getReportsByDate(LocalDateTime date) {
        return reportRepository.findAllByReportDate(date);
    }

    @Override
    public Collection<Report> getReportsByAdopterAndReviewed(long adopter_id, boolean isReviewed) {
        return reportRepository.getReportsByAdopterIdAndIsReviewed(adopter_id, isReviewed);
    }

    @Override
    public Collection<Report> getReportsByAdopter(long adopter_id) {
        return reportRepository.findAllByAdopterId(adopter_id);
    }

    @Override
    public Collection<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report editReport(long id, Report report) {
        return reportRepository.findById(id).map(
                editableReport -> {
                    editableReport.setReportDate(report.getReportDate());
                    editableReport.setPetDiet(report.getPetDiet());
                    editableReport.setPetPhoto(report.getPetPhoto());
                    editableReport.setChangeBehavior(report.getChangeBehavior());
                    editableReport.setPetHealthAndAdaptation(report.getPetHealthAndAdaptation());
                    editableReport.setAdopter(report.getAdopter());
                    return reportRepository.save(editableReport);
                }).orElse(null);
    }

    @Override
    public void deleteReport(long id) {
        logger.warn("Удаляется отчёт с ID: {}", id);
        reportRepository.deleteById(id);
    }
}
