package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.skypro.FirstTeamPetShelter.constants.VolunteerConstants.TEST_VOLUNTEER;
import static com.skypro.FirstTeamPetShelter.constants.VolunteerConstants.TEST_VOLUNTEER_IDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceImplTest {
    @Mock
    private VolunteerRepository volunteerRepository;
    @InjectMocks
    private VolunteerServiceImpl volunteerService;

    @Test
    void addVolunteer() {
        when(volunteerRepository.save(TEST_VOLUNTEER)).thenReturn(TEST_VOLUNTEER);
        assertEquals(TEST_VOLUNTEER, volunteerService.addVolunteer(TEST_VOLUNTEER));
    }

    @Test
    void getVolunteerById() {
        when(volunteerRepository.findById(1L)).thenReturn(Optional.of(TEST_VOLUNTEER_IDS));
        assertEquals(TEST_VOLUNTEER_IDS, volunteerService.getVolunteerById(1L));
    }

    @Test
    void getVolunteerByTelegramId() {
        when(volunteerRepository.findByVolunteerTelegramId(1111L)).thenReturn(TEST_VOLUNTEER_IDS);
        assertEquals(TEST_VOLUNTEER_IDS, volunteerService.getVolunteerByTelegramId(1111L));
    }

    @Test
    void getAllVolunteer() {
        when(volunteerRepository.findAll()).thenReturn(List.of(TEST_VOLUNTEER, TEST_VOLUNTEER_IDS));
        assertEquals(volunteerService.getAllVolunteer(), List.of(TEST_VOLUNTEER, TEST_VOLUNTEER_IDS));
    }
}