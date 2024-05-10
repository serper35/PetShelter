package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String volunteerName;
    private String volunteerPhoneNumber;
    private Long volunteerTelegramId;

    public Volunteer() {
    }

    public Volunteer(String volunteerName, String volunteerPhoneNumber) {
        this.volunteerName = volunteerName;
        this.volunteerPhoneNumber = volunteerPhoneNumber;
    }

    public Volunteer(String volunteerName, String volunteerPhoneNumber, Long volunteerTelegramId) {
        this.volunteerName = volunteerName;
        this.volunteerPhoneNumber = volunteerPhoneNumber;
        this.volunteerTelegramId = volunteerTelegramId;
    }

    public Volunteer(Long id, String volunteerName, String volunteerPhoneNumber, Long volunteerTelegramId) {
        this.id = id;
        this.volunteerName = volunteerName;
        this.volunteerPhoneNumber = volunteerPhoneNumber;
        this.volunteerTelegramId = volunteerTelegramId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getVolunteerPhoneNumber() {
        return volunteerPhoneNumber;
    }

    public void setVolunteerPhoneNumber(String volunteerPhoneNumber) {
        this.volunteerPhoneNumber = volunteerPhoneNumber;
    }

    public Long getVolunteerTelegramId() {
        return volunteerTelegramId;
    }

    public void setVolunteerTelegramId(Long volunteerTelegramId) {
        this.volunteerTelegramId = volunteerTelegramId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(id, volunteer.id) && Objects.equals(volunteerName, volunteer.volunteerName) && Objects.equals(volunteerPhoneNumber, volunteer.volunteerPhoneNumber) && Objects.equals(volunteerTelegramId, volunteer.volunteerTelegramId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volunteerName, volunteerPhoneNumber, volunteerTelegramId);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", volunteerName='" + volunteerName + '\'' +
                ", volunteerPhoneNumber='" + volunteerPhoneNumber + '\'' +
                ", volunteerTelegramId=" + volunteerTelegramId +
                '}';
    }
}
