package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.model.ShelterImage;
import com.skypro.FirstTeamPetShelter.repository.ShelterImageRepository;
import com.skypro.FirstTeamPetShelter.service.ShelterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShelterImageServiceImplTest {
    @Mock
    private ShelterImageRepository shelterImageRepository;

    @Mock
    private ShelterService shelterService;

    private ShelterImageServiceImpl shelterImageService;

    @BeforeEach
    void setUp() {
        shelterImageService = new ShelterImageServiceImpl(shelterImageRepository, shelterService);
    }

    @Test
    void testAddShelterAvatar() throws IOException {
        Long shelterId = 1L;
        Shelter shelter = new Shelter();
        MultipartFile shelterAvatar = new MockMultipartFile("avatar.jpg", new byte[]{1, 2, 3});
        MultipartFile shelterDrivingDirection = new MockMultipartFile("direction.jpg", new byte[]{4, 5, 6});

        when(shelterService.getShelter(shelterId)).thenReturn(shelter);

        shelterImageService.addShelterAvatarAndDrivingDirection(shelterId, shelterAvatar, shelterDrivingDirection);

        verify(shelterImageRepository, times(1)).save(any(ShelterImage.class));
    }

    @Test
    void testGetShelterImageById() {
        Long id = 1L;
        ShelterImage shelterImage = new ShelterImage();
        when(shelterImageRepository.findById(id)).thenReturn(Optional.of(shelterImage));

        ShelterImage result = shelterImageService.getShelterImageById(id);

        assertNotNull(result);
        assertEquals(shelterImage, result);
    }

    @Test
    void testGetShelterImageByShelterId() {
        Long shelterId = 1L;
        ShelterImage shelterImage = new ShelterImage();
        when(shelterImageRepository.findByShelterId(shelterId)).thenReturn(shelterImage);

        ShelterImage result = shelterImageService.getShelterImageByShelterId(shelterId);

        assertNotNull(result);
        assertEquals(shelterImage, result);
    }

    @Test
    void testEditShelterImage() throws IOException {
        Long id = 1L;
        ShelterImage shelterImage = new ShelterImage();
        MultipartFile shelterAvatar = new MockMultipartFile("avatar.jpg", new byte[]{1, 2, 3});
        MultipartFile shelterDrivingDirection = new MockMultipartFile("direction.jpg", new byte[]{4, 5, 6});

        when(shelterImageRepository.findById(id)).thenReturn(Optional.of(shelterImage));
        when(shelterImageRepository.save(shelterImage)).thenReturn(shelterImage);

        ShelterImage result = shelterImageService.editShelterImage(id, shelterAvatar, shelterDrivingDirection);

        assertNotNull(result);
        assertEquals(shelterImage, result);
        verify(shelterImageRepository, times(1)).save(shelterImage);
    }

    @Test
    void testDeleteShelterImage() {
        Long id = 1L;

        shelterImageService.deleteShelterImage(id);
        verify(shelterImageRepository, times(1)).deleteById(id);
    }
}