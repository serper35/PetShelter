package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.repository.InfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.skypro.FirstTeamPetShelter.constants.InfoConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoServiceImplTest {
    @Mock
    private InfoRepository infoRepository;
    @InjectMocks
    private InfoServiceImpl infoService;


    @Test
    void addInfo() {
        when(infoRepository.save(TEST_INFO)).thenReturn(TEST_INFO);
        assertEquals(TEST_INFO, infoService.addInfo(TEST_INFO));
    }

    @Test
    void getMessageById() {
        when(infoRepository.findById(1L)).thenReturn(Optional.of(TEST_INFO_ID));
        assertEquals(infoService.getMessage(1L), TEST_INFO_ID.getMessage());
    }

    @Test
    void getMessageByKeyWord() {
        when(infoRepository.findByKeyWord("KW")).thenReturn(Optional.of(TEST_INFO_KW));
        assertEquals(infoService.getMessage("KW"), TEST_INFO_KW.getMessage());
    }

    @Test
    void getAllInfo() {
        when(infoRepository.findAll()).thenReturn(List.of(TEST_INFO, TEST_INFO_ID, TEST_INFO_KW));
        assertEquals(infoService.getAllInfo(), List.of(TEST_INFO, TEST_INFO_ID, TEST_INFO_KW));
    }
}