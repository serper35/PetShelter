package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.Pet;
import com.skypro.FirstTeamPetShelter.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("petservice")
public class PetServiceController {
    private PetService petService;

    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(petService.addPet(pet));
    }

    @GetMapping({"id"})
    public ResponseEntity<Pet> getPet(@PathVariable long id) {
        return ResponseEntity.ok(petService.getPet(id));
    }

    @GetMapping
    public Collection<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @PutMapping({"id"})
    public ResponseEntity<Pet> editPet(@PathVariable Long id, @RequestBody Pet pet) {
        return ResponseEntity.ok(petService.editPet(id, pet));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }
}
