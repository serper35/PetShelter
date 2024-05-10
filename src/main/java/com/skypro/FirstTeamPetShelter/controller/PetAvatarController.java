package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.DTO.PetAvatarDTO;
import com.skypro.FirstTeamPetShelter.mapper.PetAvatarMapper;
import com.skypro.FirstTeamPetShelter.model.PetAvatar;
import com.skypro.FirstTeamPetShelter.service.PetAvatarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController("pet")
public class PetAvatarController {
    private final PetAvatarService petAvatarService;
    private final PetAvatarMapper petAvatarMapper;

    public PetAvatarController(PetAvatarService petAvatarService, PetAvatarMapper petAvatarMapper) {
        this.petAvatarService = petAvatarService;
        this.petAvatarMapper = petAvatarMapper;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addPetAvatar(
            @PathVariable Long id,
            @RequestParam MultipartFile petAvatarFile) throws IOException {
        if (petAvatarFile.getSize() > 1024 * 1024 * 5) {
            return ResponseEntity.badRequest().body("Аватар питомца должен быть менее 5 мегабайт");
        }
        petAvatarService.addPetAvatar(id, petAvatarFile);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}/avatar-from-db") // Получение аватара питомца по id
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        PetAvatar petAvatar = petAvatarService.getPetAvatarById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(petAvatar.getMediaType()));
        headers.setContentLength(petAvatar.getSmallAvatar().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(petAvatar.getSmallAvatar());
    }

    @GetMapping("avatars")
    public List<PetAvatarDTO> getPaginatedAvatars(
            @RequestParam int pageNumber,
            @RequestParam int pageSize
    ) {
        return petAvatarService.getAllPetAvatars(pageNumber, pageSize)
                .stream()
                .map(petAvatarMapper::matToDTO)
                .collect(Collectors.toList());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<PetAvatar> deletePetAvatarByAvatarId(Long id) {
        petAvatarService.deletePetAvatar(id);
        return ResponseEntity.ok().build();
    }
}
