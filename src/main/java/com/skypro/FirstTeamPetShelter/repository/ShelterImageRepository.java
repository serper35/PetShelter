package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.ShelterImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterImageRepository extends JpaRepository<ShelterImage, Long> {
    ShelterImage findByShelterId(long shelterId);
}
