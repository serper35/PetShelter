package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserApp> add(@RequestBody UserApp userApp) {
        return ResponseEntity.ok(userService.addUser(userApp));
    }

    @PutMapping
    public ResponseEntity<UserApp> update(@RequestBody UserApp userApp) {
        return ResponseEntity.ok(userService.editUser(userApp));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserApp> get(@PathVariable Long id) {
        UserApp userApp = userService.getUser(id);

        return ResponseEntity.ok(userApp);
    }

    @GetMapping("/tgId/{id}")
    public ResponseEntity<UserApp> getByTgId(@PathVariable Long id) {
        UserApp userApp = userService.getUserByTelegramId(id);

        return ResponseEntity.ok(userApp);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<UserApp>> getAll() {
        return ResponseEntity.ok(userService.getAllUser());
    }
}
