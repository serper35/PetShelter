package com.skypro.FirstTeamPetShelter.service.bot.helper;

import com.skypro.FirstTeamPetShelter.model.Adopter;
import com.skypro.FirstTeamPetShelter.model.Report;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.service.AdopterService;
import com.skypro.FirstTeamPetShelter.service.ReportService;
import com.skypro.FirstTeamPetShelter.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BotHelper {
    private final UserService userService;
    private final AdopterService adopterService;
    private final ReportService reportService;

    public BotHelper(UserService userService, AdopterService adopterService, ReportService reportService) {
        this.userService = userService;
        this.adopterService = adopterService;
        this.reportService = reportService;
    }

    public List<UserApp> getUsersCallingVolunteer() {
        List<UserApp> users = userService.getAllUser().stream().toList();
        List<UserApp> result = new ArrayList<>();
        for (UserApp userApp : users) {
            if(!userApp.isContacted()) {
                result.add(userApp);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<Adopter> getAdoptersCallingVolunteer() {
        List<Adopter> adopters = adopterService.getAllAdopters().stream().toList();
        List<Adopter> result = new ArrayList<>();
        for (Adopter adopter : adopters) {
            if (!adopter.isContacted()) {
                result.add(adopter);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<Report> getAdoptersReportCheck() {
        List<Report> reports = reportService.getAllReports().stream().toList();
        List<Report> result = new ArrayList<>();
        for (Report report : reports) {
            if (!report.isReviewed()) {
                result.add(report);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<UserApp> getUsersBecomeAdoptive() {
        List<UserApp> users = userService.getAllUser().stream().toList();
        List<UserApp> result = new ArrayList<>();
        for (UserApp userApp : users) {
            if(userApp.isBecomeAdoptive()) {
                result.add(userApp);
            }
        }
        return Collections.unmodifiableList(result);
    }
}
