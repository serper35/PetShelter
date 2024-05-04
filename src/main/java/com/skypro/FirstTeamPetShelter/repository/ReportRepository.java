package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByReportDate(LocalDateTime date);
    List<Report> findAllByAdopterId(Long id);

    List<Report> getReportsByAdopterIdAndIsReviewed(Long adopterId, boolean isReviewed);
}
