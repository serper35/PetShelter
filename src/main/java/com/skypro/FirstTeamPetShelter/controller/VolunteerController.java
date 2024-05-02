package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.Volunteer;
import com.skypro.FirstTeamPetShelter.service.VolunteerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("volunteer/")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public Volunteer addVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.addVolunteer(volunteer);
    }

    @PutMapping
    public ResponseEntity<Volunteer> editVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer foundVolunteer = volunteerService.editVolunteer(volunteer);
        if (foundVolunteer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundVolunteer);
    }

    @GetMapping
    public Collection<Volunteer> getAllVolunteer() {
        return volunteerService.getAllVolunteer();
    }

    @GetMapping("id/{id}")
    public Volunteer getVolunteerById(@PathVariable Long id) {
        return volunteerService.getVolunteerById(id);
    }

    @GetMapping("telegramId/{telegramId}")
    public Volunteer getVolunteerByTelegramId(@PathVariable Long telegramId) {
        return volunteerService.getVolunteerByTelegramId(telegramId);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok().build();
    }
}
