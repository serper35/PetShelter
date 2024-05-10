package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetServiceRepository extends JpaRepository<Pet, Long> {
    // todo: Реализовать пагинацию
}
