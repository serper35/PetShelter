package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.enums.PetHealth;
import com.skypro.FirstTeamPetShelter.exception.UserNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Pet;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.repository.PetServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

    @Mock
    private PetServiceRepository repository;
    @InjectMocks
    private PetServiceImpl petService;

    private Pet pet;
    private Pet pet1;
    private Long id;

    @BeforeEach
    void setup() {
        id = 1L;
        pet = new Pet("Type", "petBreed", "petGender", PetHealth.HEALTHY, "petName", 10, "petDescription");
        pet1 = new Pet("Type", "petBreed", "petGender", PetHealth.HEALTHY, "petName", 10, "petDescription");
        pet1.setId(id);
    }
    @Test
    void testAddPet() {
        when(repository.save(pet)).thenReturn(pet1);

        Pet fromDb = petService.addPet(pet);

        assertNotNull(fromDb);
        assertEquals(pet1, fromDb);
        verify(repository, times(1)).save(pet);
    }

    @Test
    void testGetPet() {
        when(repository.findById(id)).thenReturn(Optional.of(pet1));

        Pet fromDb = petService.getPet(id);

        assertNotNull(fromDb);
        assertEquals(pet1, fromDb);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testGetPetWhenNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        Pet fromDb = petService.getPet(id);

        assertNull(fromDb);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void editPet_shouldUpdatePetIfExists() {
        when(repository.findById(id)).thenReturn(Optional.of(pet1));
        when(repository.save(pet1)).thenReturn(pet1);

        Pet fromDb = petService.editPet(id, pet1);

        assertNotNull(fromDb);
        assertEquals(pet1, fromDb);
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(pet1);
    }

    @Test
    void editPet_shouldReturnNullIfNotExists() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        Pet fromDb = petService.editPet(id, pet1);

        assertNull(fromDb);
        verify(repository, times(1)).findById(id);
        verify(repository, times(0)).save(any(Pet.class));
    }

    @Test
    void deletePet_shouldDeletePetById() {
        petService.deletePet(id);

        verify(repository, times(1)).deleteById(id);
    }
}