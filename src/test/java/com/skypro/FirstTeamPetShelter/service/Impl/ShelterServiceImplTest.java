package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.ShelterNotFoundException;
import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.repository.ShelterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShelterServiceImplTest {

    @InjectMocks
    ShelterServiceImpl shelterService;

    @Mock
    ShelterRepository shelterRepository;
    private Shelter shelter;
    private Shelter shelter1;
    private Long id;

    @BeforeEach
    void setUp() {
        shelter = new Shelter("zoo", "adress", "security", "info");
        shelter1 = new Shelter("zoo", "adress", "security", "info");
        shelter1.setId(1L);
        id = 1L;
    }

    @Test
    void addShelterTest() {
        when(shelterRepository.save(shelter)).thenReturn(shelter1);

        Shelter saved = shelterService.addShelter(shelter);

        assertNotNull(saved);
        assertEquals(id, saved.getId());
        assertEquals(shelter.getShelterName(), saved.getShelterName());
        assertEquals(shelter.getSafetyInformation(), saved.getSafetyInformation());
        assertEquals(shelter.getShelterInfo(), saved.getShelterInfo());
        assertEquals(shelter.getSecurityContactDetail(), saved.getSecurityContactDetail());
        verify(shelterRepository, times(1)).save(shelter);
    }

    @Test
    void getShelterTest() {
        when(shelterRepository.findById(id)).thenReturn(Optional.of(shelter1));

        Shelter fromDb = shelterService.getShelter(1);

        assertNotNull(fromDb);
        assertEquals(id, fromDb.getId());
        assertEquals(shelter.getShelterName(), fromDb.getShelterName());
        assertEquals(shelter.getSafetyInformation(), fromDb.getSafetyInformation());
        assertEquals(shelter.getShelterInfo(), fromDb.getShelterInfo());
        assertEquals(shelter.getSecurityContactDetail(), fromDb.getSecurityContactDetail());
        verify(shelterRepository, times(1)).findById(1L);
    }

    @Test
    void testGetShelterUserNotFound() {
        when(shelterRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ShelterNotFoundException.class, () -> shelterService.getShelter(id));
        verify(shelterRepository, times(1)).findById(id);
    }

    @Test
    void getAllSheltersTest() {
        List<Shelter> shelters = Arrays.asList(shelter, shelter1);

        when(shelterRepository.findAll()).thenReturn(shelters);

        Collection<Shelter> fromDb = shelterService.getAllShelters();

        assertNotNull(fromDb);
        assertEquals(shelters, fromDb);
        verify(shelterRepository, times(1)).findAll();
    }

    @Test
    void testGetAllSheltersEmpty() {
        when(shelterRepository.findAll()).thenReturn(Collections.emptyList());

        Collection<Shelter> fromDb = shelterService.getAllShelters();

        assertTrue(fromDb.isEmpty());
        verify(shelterRepository, times(1)).findAll();
    }

    @Test
    void testEditShelter() {
        shelter1.setShelterName("asfaf");
        when(shelterRepository.findById(shelter1.getId())).thenReturn(Optional.of(shelter1));
        when(shelterRepository.save(shelter1)).thenReturn(shelter1);

        Shelter fromDb = shelterService.editShelter(shelter1);

        assertNotNull(fromDb);
        assertEquals(shelter1, fromDb);
        verify(shelterRepository, times(1)).findById(id);
        verify(shelterRepository, times(1)).save(shelter1);
    }

    @Test
    void testEditShelterUserNotFound() {
        when(shelterRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ShelterNotFoundException.class, () -> shelterService.editShelter(shelter1));
        verify(shelterRepository, times(1)).findById(id);
        verify(shelterRepository, times(0)).save(shelter1);
    }

    @Test
    void deleteShelter() {
        when(shelterRepository.findById(id)).thenReturn(Optional.of(shelter1));

        shelterService.deleteShelter(id);

        verify(shelterRepository, times(1)).findById(id);
        verify(shelterRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteShelterNotFound() {
        when(shelterRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ShelterNotFoundException.class, () -> shelterService.deleteShelter(id));
        verify(shelterRepository, times(1)).findById(id);
        verify(shelterRepository, times(0)).deleteById(id);
    }
}