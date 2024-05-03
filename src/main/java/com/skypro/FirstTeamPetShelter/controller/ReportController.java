package com.skypro.FirstTeamPetShelter.controller;

import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("report/")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Report addReport(@RequestBody Report report) {
        return reportService.addReport(report);
    }

    @Operation(
            summary = "Получение отчета по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёт",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
            },
            tags = "Report"
    )
    @GetMapping("id/{id}")
    public Report getReportById(@PathVariable(name = "ID отчёта") long id) {
        return reportService.getReportById(id);
    }

    @Operation(
            summary = "Получение списка отчетов по дате",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёты по дате",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report[].class)
                            )
                    )
            },
            tags = "Report"
    )
    @GetMapping("date/{date}")
    public Collection<Report> getReportsByDate(@PathVariable(name = "Дата отчётов") LocalDateTime date) {
        return reportService.getReportsByDate(date);
    }

    @Operation(
            summary = "Получение списка отчётов по усыновителю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёты по усыновителю"
                    )
            },
            tags = "Report"
    )
    @GetMapping("adopter/{adopter_id}")
    public Collection<Report> getReportsByAdopter(@PathVariable(name = "ID усыновителя") long adopter_id) {
        return reportService.getReportsByAdopter(adopter_id);
    }

    @Operation(
            summary = "Получение всех отчётов из БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отчёты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report[].class)
                            )
                    )
            },
            tags = "Report"
    )
    @GetMapping
    public Collection<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @Operation(
            summary = "Редактирование отчета по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Редактируемый отчёт",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
            },
            tags = "Report"
    )
    @PutMapping("edit/{id}/{report}")
    public Report editReport(@PathVariable long id, @PathVariable Report report) {
        return reportService.editReport(id, report);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление отчёта по ID"
            )
    })
    @DeleteMapping("delete/{id}")
    public void deleteReport(@PathVariable long id) {
        reportService.deleteReport(id);
    }
}
