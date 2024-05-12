package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetServiceRepository extends JpaRepository<Pet, Long> {
    // todo: Реализовать пагинацию
    List<Pet> findAllByPetType(String petType);
    Pet findByPetOwnerId(long pet_owner_id);
    Pet findByPetPotentialOwnerId(long pet_potential_owner_id);
}
