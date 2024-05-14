package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.ReportNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @Mock
    private ReportRepository reportRepository;

    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        reportService = new ReportServiceImpl(reportRepository);
    }

    @Test
    void testAddReport() {
        Report report = new Report();
        when(reportRepository.save(report)).thenReturn(report);

        Report savedReport = reportService.addReport(report);

        assertNotNull(savedReport);
        verify(reportRepository, times(1)).save(report);
    }

    @Test
    void testGetReportById() {
        long id = 1L;
        Report report = new Report();
        when(reportRepository.findById(id)).thenReturn(Optional.of(report));

        Report foundReport = reportService.getReportById(id);

        assertNotNull(foundReport);
        assertEquals(report, foundReport);
    }

    @Test
    void testGetReportByIdNotFound() {
        long id = 1L;
        when(reportRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.getReportById(id));
    }

    @Test
    void testGetReportsByDate() {
        LocalDateTime date = LocalDateTime.now();
        Collection<Report> reports = new ArrayList<>();
        when(reportRepository.findAllByReportDate(date)).thenReturn((List<Report>) reports);

        Collection<Report> foundReports = reportService.getReportsByDate(date);

        assertNotNull(foundReports);
        assertEquals(reports, foundReports);
    }

    @Test
    void testGetReportsByAdopterAndReviewed() {
        long adopterId = 1L;
        boolean isReviewed = true;
        Collection<Report> reports = new ArrayList<>();
        when(reportRepository.getReportsByAdopterIdAndIsReviewed(adopterId, isReviewed)).thenReturn((List<Report>) reports);

        Collection<Report> foundReports = reportService.getReportsByAdopterAndReviewed(adopterId, isReviewed);

        assertNotNull(foundReports);
        assertEquals(reports, foundReports);
    }

    @Test
    void testGetReportsByAdopter() {
        long adopterId = 1L;
        Collection<Report> reports = new ArrayList<>();
        when(reportRepository.findAllByAdopterId(adopterId)).thenReturn((List<Report>) reports);

        Collection<Report> foundReports = reportService.getReportsByAdopter(adopterId);

        assertNotNull(foundReports);
        assertEquals(reports, foundReports);
    }

    @Test
    void testGetAllReports() {
        Collection<Report> reports = new ArrayList<>();
        when(reportRepository.findAll()).thenReturn((List<Report>) reports);

        Collection<Report> foundReports = reportService.getAllReports();

        assertNotNull(foundReports);
        assertEquals(reports, foundReports);
    }

    @Test
    void testEditReport() {
        long id = 1L;
        Report existingReport = new Report();
        Report updatedReport = new Report();
        updatedReport.setReportDate(LocalDateTime.now());
        updatedReport.setPetDiet("New pet diet");
        updatedReport.setPetPhoto(new byte[]{1, 2, 3});
        updatedReport.setChangeBehavior("New behavior change");
        updatedReport.setPetHealthAndAdaptation("New pet health and adaptation");
        updatedReport.setAdopter(new Adopter());

        when(reportRepository.findById(id)).thenReturn(Optional.of(existingReport));
        when(reportRepository.save(existingReport)).thenReturn(existingReport);

        Report editedReport = reportService.editReport(id, updatedReport);

        assertNotNull(editedReport);
        assertEquals(existingReport, editedReport);
        verify(reportRepository, times(1)).save(existingReport);
    }

    @Test
    void testDeleteReport() {
        long id = 1L;

        reportService.deleteReport(id);

        verify(reportRepository, times(1)).deleteById(id);
    }
}