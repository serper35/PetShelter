package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.ShelterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterImageRepository extends JpaRepository<ShelterImage, Long> {
    ShelterImage findByShelterId(long shelterId);
}
