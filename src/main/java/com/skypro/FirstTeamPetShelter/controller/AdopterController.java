package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.service.AdopterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("adopter/")
public class AdopterController {
    private final AdopterService adopterService;

    public AdopterController(AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @PostMapping
    public ResponseEntity<Adopter> addAdopter(@RequestBody Adopter adopter) {
        return ResponseEntity.ok(adopterService.addAdopter(adopter));
    }

    @PutMapping
    public ResponseEntity<Adopter> update(@RequestBody Adopter adopter) {
        return ResponseEntity.ok(adopterService.editAdopter(adopter));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adopterService.deleteAdopter(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Adopter> get(@PathVariable Long id) {
        Adopter adopter = adopterService.getAdopter(id);

        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/tgId/{id}")
    public ResponseEntity<Adopter> getByTgId(@PathVariable Long id) {
        Adopter adopter = adopterService.getAdopterByTelegramId(id);

        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Adopter>> getAll() {
        return ResponseEntity.ok(adopterService.getAllAdopters());
    }
}
