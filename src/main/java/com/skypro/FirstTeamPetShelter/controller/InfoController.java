package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.Info;
import com.skypro.FirstTeamPetShelter.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
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

    @Operation(
            summary = "Получение всех информационных сообщений из БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информационные сообщения",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Info[].class)
                            )
                    )
            },
            tags = "Info"
    )
    @GetMapping
    public Collection<Info> getAllInfo() {
        return infoService.getAllInfo();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информационного сообщения по ID",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("message/id/{id}")
    public String getMessage(@PathVariable(name = "ID сообщения") long id) {
        return infoService.getMessage(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информационного сообщения по ключевому слову"
            )
    })
    @GetMapping("message/key/{keyWord}")
    public String getMessage(@Parameter(description = "Ключевое слово", example = "hello") @PathVariable String keyWord) {
        return infoService.getMessage(keyWord);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление информационного сообщения по ID"
            )
    })
    @DeleteMapping("delete/{id}")
    public void deleteInfo(@PathVariable(name = "ID сообщения") long id) {
        infoService.deleteInfo(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление информационного сообщения по ключевому слову"
            )
    })
    @DeleteMapping("delete/{keyWord}")
    public void deleteInfo(@PathVariable(name = "Ключевое слово сообщения") String keyWord) {
        infoService.deleteInfo(keyWord);
    }
}
