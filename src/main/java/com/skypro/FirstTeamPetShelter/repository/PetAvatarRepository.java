package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.PetAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetAvatarRepository extends JpaRepository<PetAvatar, Long> {
    //Optional<PetAvatar> findById(Long id);
    PetAvatar findPetAvatarByPetId(long petId);
}
