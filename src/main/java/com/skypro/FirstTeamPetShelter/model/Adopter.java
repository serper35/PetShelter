package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adopterName;
    private String adopterPhoneNumber;
    private Long adopterTelegramId;
    @OneToMany(mappedBy = "adopter")
    private Collection<Report> adopterReports;

    public Adopter() {
    }

    public Adopter(String adopterName, String adopterPhoneNumber, Long adopterTelegramId) {
        this.adopterName = adopterName;
        this.adopterPhoneNumber = adopterPhoneNumber;
        this.adopterTelegramId = adopterTelegramId;
    }

    public Adopter(String adopterName, String adopterPhoneNumber, Long adopterTelegramId, Collection<Report> adopterReports) {
        this.adopterName = adopterName;
        this.adopterPhoneNumber = adopterPhoneNumber;
        this.adopterTelegramId = adopterTelegramId;
        this.adopterReports = adopterReports;
    }

    public Adopter(Long id, String adopterName, String adopterPhoneNumber, Long adopterTelegramId, Collection<Report> adopterReports) {
        this.id = id;
        this.adopterName = adopterName;
        this.adopterPhoneNumber = adopterPhoneNumber;
        this.adopterTelegramId = adopterTelegramId;
        this.adopterReports = adopterReports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getAdopterPhoneNumber() {
        return adopterPhoneNumber;
    }

    public void setAdopterPhoneNumber(String adopterPhoneNumber) {
        this.adopterPhoneNumber = adopterPhoneNumber;
    }

    public Long getAdopterTelegramId() {
        return adopterTelegramId;
    }

    public void setAdopterTelegramId(Long adopterTelegramId) {
        this.adopterTelegramId = adopterTelegramId;
    }

    public Collection<Report> getAdopterReports() {
        return adopterReports;
    }

    public void setAdopterReports(Collection<Report> adopterReports) {
        this.adopterReports = adopterReports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adopter adopter = (Adopter) o;
        return Objects.equals(id, adopter.id) && Objects.equals(adopterName, adopter.adopterName) && Objects.equals(adopterPhoneNumber, adopter.adopterPhoneNumber) && Objects.equals(adopterTelegramId, adopter.adopterTelegramId) && Objects.equals(adopterReports, adopter.adopterReports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adopterName, adopterPhoneNumber, adopterTelegramId, adopterReports);
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", adopterName='" + adopterName + '\'' +
                ", adopterPhoneNumber='" + adopterPhoneNumber + '\'' +
                ", adopterTelegramId=" + adopterTelegramId +
                ", adopterReports=" + adopterReports +
                '}';
    }
}
