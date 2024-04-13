package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.Info;
import com.skypro.FirstTeamPetShelter.service.InfoService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("info/")
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @PostMapping
    public Info addInfo(@RequestBody Info info) {
        return infoService.addInfo(info);
    }

    @GetMapping
    public Collection<Info> getAllInfo() {
        return infoService.getAllInfo();
    }

    @GetMapping("message/{id}")
    public String getMessage(@PathVariable long id) {
        return infoService.getMessage(id);
    }

    @GetMapping("message/{keyWord}")
    public String getMessage(@PathVariable String keyWord) {
        return infoService.getMessage(keyWord);
    }

    @DeleteMapping("delete/{id}")
    public void deleteInfo(@PathVariable long id) {
        infoService.deleteInfo(id);
    }

    @DeleteMapping("delete/{keyWord}")
    public void deleteInfo(@PathVariable String keyWord) {
        infoService.deleteInfo(keyWord);
    }
}
