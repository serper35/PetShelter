package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.UserNotFoundException;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.repository.UserRepository;
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
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserApp userApp;

    @BeforeEach
    void setUp() {
        userApp = new UserApp(1L, "Vova", "Ivanov", 1L);
    }

    @Test
    void testAddUser() {
        when(userRepository.save(userApp)).thenReturn(userApp);

        UserApp savedUserApp = userService.addUser(userApp);

        assertNotNull(savedUserApp);
        assertEquals(userApp, savedUserApp);
        verify(userRepository, times(1)).save(userApp);
    }

    @Test
    void testGetUser() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(userApp));

        UserApp foundUserApp = userService.getUser(userId);

        assertNotNull(foundUserApp);
        assertEquals(userApp, foundUserApp);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserNotFound() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetAllUser() {
        List<UserApp> userApps = Arrays.asList(userApp, new UserApp(2L, "Ivan", "Fa", 67890L));
        when(userRepository.findAll()).thenReturn(userApps);

        Collection<UserApp> allUserApps = userService.getAllUser();

        assertNotNull(allUserApps);
        assertEquals(2, allUserApps.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUserEmpty() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        Collection<UserApp> allUserApps = userService.getAllUser();

        assertNotNull(allUserApps);
        assertTrue(allUserApps.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testEditUser() {
        long userId = 1L;
        UserApp updatedUserApp = new UserApp(userId, "Vova", "Ivanov", 234234L);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userApp));
        when(userRepository.save(updatedUserApp)).thenReturn(updatedUserApp);

        UserApp editedUserApp = userService.editUser(updatedUserApp);

        assertNotNull(editedUserApp);
        assertEquals(updatedUserApp, editedUserApp);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(updatedUserApp);
    }

    @Test
    void testEditUserNotFound() {
        long userId = 1L;
        UserApp updatedUserApp = new UserApp(userId, "Vova", "Ivanov", 234234L);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.editUser(updatedUserApp));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any(UserApp.class));
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(userApp));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUserNotFound() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    void testGetUserByTelegramId() {
        long telegramId = 12345;
        when(userRepository.findByUserTelegramId(telegramId)).thenReturn(userApp);

        UserApp foundUserApp = userService.getUserByTelegramId(telegramId);

        assertNotNull(foundUserApp);
        assertEquals(userApp, foundUserApp);
        verify(userRepository, times(1)).findByUserTelegramId(telegramId);
    }
}