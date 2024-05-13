package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.model.Pet;
import com.skypro.FirstTeamPetShelter.model.PetAvatar;
import com.skypro.FirstTeamPetShelter.repository.PetAvatarRepository;
import com.skypro.FirstTeamPetShelter.service.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetAvatarServiceImplTest {
    @Mock
    private PetService petService;

    @Mock
    private PetAvatarRepository petAvatarRepository;

    @InjectMocks
    private PetAvatarServiceImpl petAvatarService;

    @Test
    void testGetPetAvatarById() {
        Long avatarId = 1L;
        PetAvatar petAvatar = new PetAvatar();
        when(petAvatarRepository.findById(avatarId)).thenReturn(Optional.of(petAvatar));

        PetAvatar result = petAvatarService.getPetAvatarById(avatarId);

        assertNotNull(result);
        assertEquals(petAvatar, result);
        verify(petAvatarRepository, times(1)).findById(avatarId);
    }

    @Test
    void testDeletePetAvatar() {
        Long avatarId = 1L;

        petAvatarService.deletePetAvatar(avatarId);

        verify(petAvatarRepository, times(1)).deleteById(avatarId);
    }

    @Test
    void testFindPetAvatar_ExistingId() {
        Long id = 1L;
        PetAvatar expectedPetAvatar = new PetAvatar();
        expectedPetAvatar.setId(id);
        when(petAvatarRepository.findById(id)).thenReturn(Optional.of(expectedPetAvatar));

        PetAvatar result = petAvatarService.findPetAvatar(id);

        assertNotNull(result);
        assertEquals(expectedPetAvatar, result);
    }

    @Test
    void testFindPetAvatar_NonExistingId() {
        Long id = 2L;
        when(petAvatarRepository.findById(id)).thenReturn(Optional.empty());

        PetAvatar result = petAvatarService.findPetAvatar(id);

        assertNotNull(result);
        assertEquals(new PetAvatar(), result);
    }
}