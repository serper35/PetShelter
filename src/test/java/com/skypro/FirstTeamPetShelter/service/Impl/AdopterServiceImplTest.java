package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.repository.AdopterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.skypro.FirstTeamPetShelter.constants.AdopterConstants.TEST_ADOPTER;
import static com.skypro.FirstTeamPetShelter.constants.AdopterConstants.TEST_ADOPTER_FULL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdopterServiceImplTest {
    @Mock
    private AdopterRepository adopterRepository;
    @InjectMocks
    private AdopterServiceImpl adopterService;

    @Test
    void addAdopter() {
        when(adopterRepository.save(TEST_ADOPTER)).thenReturn(TEST_ADOPTER);
        assertEquals(TEST_ADOPTER, adopterService.addAdopter(TEST_ADOPTER));
    }

    @Test
    void getAllAdopters() {
        when(adopterRepository.findAll()).thenReturn(List.of(TEST_ADOPTER, TEST_ADOPTER_FULL));
        assertEquals(adopterService.getAllAdopters(), List.of(TEST_ADOPTER, TEST_ADOPTER_FULL));
    }

    @Test
    void getAdopterById() {
        when(adopterRepository.findById(1L)).thenReturn(Optional.of(TEST_ADOPTER_FULL));
        assertEquals(adopterService.getAdopter(1L), TEST_ADOPTER_FULL);
    }

    @Test
    void getAdopterByTelegramId() {
        when(adopterRepository.findByAdopterTelegramId(1111L)).thenReturn(TEST_ADOPTER_FULL);
        assertEquals(adopterService.getAdopterByTelegramId(1111L), TEST_ADOPTER_FULL);
    }
}