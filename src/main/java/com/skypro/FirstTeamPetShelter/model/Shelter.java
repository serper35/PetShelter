package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shelterName;

    @Column(length = 512)
    private String shelterInfo;             // Расписание работы и адрес

    @Column(length = 2048)
    private String securityContactDetail;   // Контактные данные охраны

    @Column(length = 65535)
    private String safetyInformation;       // Информация по безопасности на территории приюта

    private String shelterType;

    public Shelter() {
    }

    public Shelter(String shelterName, String shelterInfo, String securityContactDetail, String safetyInformation) {
        this.shelterName = shelterName;
        this.shelterInfo = shelterInfo;
        this.securityContactDetail = securityContactDetail;
        this.safetyInformation = safetyInformation;
    }

    public Shelter(Long id, String shelterName, String shelterInfo, String securityContactDetail, String safetyInformation, String shelterType) {
        this.id = id;
        this.shelterName = shelterName;
        this.shelterInfo = shelterInfo;
        this.securityContactDetail = securityContactDetail;
        this.safetyInformation = safetyInformation;
        this.shelterType = shelterType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterInfo() {
        return shelterInfo;
    }

    public void setShelterInfo(String shelterInfo) {
        this.shelterInfo = shelterInfo;
    }

    public String getShelterType() {
        return shelterType;
    }

    public void setShelterType(String shelterType) {
        this.shelterType = shelterType;
    }

    public String getSecurityContactDetail() {
        return securityContactDetail;
    }

    public void setSecurityContactDetail(String securityContactDetail) {
        this.securityContactDetail = securityContactDetail;
    }

    public String getSafetyInformation() {
        return safetyInformation;
    }

    public void setSafetyInformation(String safetyInformation) {
        this.safetyInformation = safetyInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(id, shelter.id) && Objects.equals(shelterName, shelter.shelterName) && Objects.equals(shelterInfo, shelter.shelterInfo) && Objects.equals(securityContactDetail, shelter.securityContactDetail) && Objects.equals(safetyInformation, shelter.safetyInformation) && Objects.equals(shelterType, shelter.shelterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shelterName, shelterInfo, securityContactDetail, safetyInformation, shelterType);
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", shelterName='" + shelterName + '\'' +
                ", shelterInfo='" + shelterInfo + '\'' +
                ", securityContactDetail='" + securityContactDetail + '\'' +
                ", safetyInformation='" + safetyInformation + '\'' +
                ", shelterType='" + shelterType + '\'' +
                '}';
    }
}
