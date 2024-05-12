package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.model.Pet;
import com.skypro.FirstTeamPetShelter.repository.PetServiceRepository;
import com.skypro.FirstTeamPetShelter.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetServiceRepository petServiceRepository;
    private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

    public PetServiceImpl(PetServiceRepository petServiceRepository) {
        this.petServiceRepository = petServiceRepository;
    }

    @Override
    public Pet addPet(Pet pet) {
        logger.info("Log info: Method addPet is invoke.");
        return petServiceRepository.save(pet);
    }

    @Override
    public Pet getPet(long id) {
        logger.info("Log info: Method getPet is invoke.");
        return petServiceRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Pet> getAllPets() {
        logger.info("Log info: Method getAllPets is invoke.");
        // todo: Реализовать пагинацию
        return petServiceRepository.findAll();
    }

    @Override
    public Collection<Pet> getPetsByPetType(String petType) {
        return petServiceRepository.findAllByPetType(petType);
    }

    @Override
    public Pet getPetByPotentialOwner(long potential_owner_id) {
        return petServiceRepository.findByPetPotentialOwnerId(potential_owner_id);
    }

    @Override
    public Pet getPetByOwner(long owner_id) {
        return petServiceRepository.findByPetOwnerId(owner_id);
    }

    @Override
    public Pet editPet(long id, Pet pet) {
        logger.info("Log info: Method editPet is invoke.");
        return petServiceRepository.findById(id)
                .map(editablePet -> {
                    editablePet.setPetType(pet.getPetType());
                    editablePet.setPetBreed(pet.getPetBreed());
                    editablePet.setPetGender(pet.getPetGender());
                    editablePet.setPetHealth(pet.getPetHealth());
                    editablePet.setPetName(pet.getPetName());
                    editablePet.setPetAge(pet.getPetAge());
                    editablePet.setPetDescription(pet.getPetDescription());
                    return petServiceRepository.save(editablePet);
                }).orElse(null);
    }

    @Override
    public void deletePet(long id) {
        logger.info("Log info: Method deletePet is invoke.");
        petServiceRepository.deleteById(id);
    }
}
