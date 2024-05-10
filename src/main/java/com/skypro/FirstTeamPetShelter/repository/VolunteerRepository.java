package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.Volunteer;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Volunteer findByVolunteerTelegramId(Long volunteerTelegramId);
}
