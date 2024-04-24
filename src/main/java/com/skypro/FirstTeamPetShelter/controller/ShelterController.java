package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.Shelter;
import com.skypro.FirstTeamPetShelter.service.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("shelter")
public class ShelterController {
    private ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Shelter> get(@PathVariable long id) {
        return ResponseEntity.ok(shelterService.getShelter(id));

    }

    @PostMapping
    public ResponseEntity<Shelter> add(@RequestBody Shelter shelter) {
        return ResponseEntity.ok(shelterService.addShelter(shelter));
    }

    @PutMapping
    public ResponseEntity<Shelter> edit(@RequestBody Shelter shelter) {
        return ResponseEntity.ok(shelterService.editShelter(shelter));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        shelterService.deleteShelter(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Shelter>> getAll() {
        return ResponseEntity.ok(shelterService.getAllShelters());
    }
}
