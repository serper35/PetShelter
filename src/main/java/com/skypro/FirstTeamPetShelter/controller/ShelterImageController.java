package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.ShelterImage;
import com.skypro.FirstTeamPetShelter.service.ShelterImageService;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Transactional
@RequestMapping("shelter-image/")
public class ShelterImageController {
    private final ShelterImageService shelterImageService;

    public ShelterImageController(ShelterImageService shelterImageService) {
        this.shelterImageService = shelterImageService;
    }

    @PostMapping(value = "{shelterId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addShelterAvatarAndDrivingDirection(
            @PathVariable Long shelterId,
            @RequestParam MultipartFile shelterAvatar,
            @RequestParam MultipartFile shelterDrivingDirection) throws IOException {
        shelterImageService.addShelterAvatarAndDrivingDirection(shelterId, shelterAvatar, shelterDrivingDirection);
    }

    @GetMapping("id/{id}")
    public ShelterImage getShelterImageById(@PathVariable long id) {
        return shelterImageService.getShelterImageById(id);
    }

    @GetMapping("shelterId/{shelterId}")
    public ShelterImage getShelterImageByShelterId(@PathVariable long shelterId) {
        return shelterImageService.getShelterImageByShelterId(shelterId);
    }

    @PutMapping(value = "edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ShelterImage editShelterImage(
            @PathVariable long id,
            @RequestParam MultipartFile shelterAvatar,
            @RequestParam MultipartFile shelterDrivingDirection) throws IOException {
        return shelterImageService.editShelterImage(id, shelterAvatar, shelterDrivingDirection);
    }

    @DeleteMapping("delete/{id}")
    public void deleteShelterImage(@PathVariable long id) {
        shelterImageService.deleteShelterImage(id);
    }
}
