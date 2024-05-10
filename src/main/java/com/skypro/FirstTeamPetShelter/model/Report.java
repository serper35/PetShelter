package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] petPhoto;
    private String petDiet;
    private String petHealthAndAdaptation;
    private String changeBehavior;
    private Date reportDate;
    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopter;

    public Report() {
    }

    public Report(byte[] petPhoto, String petDiet, String petHealthAndAdaptation, String changeBehavior) {
        this.petPhoto = petPhoto;
        this.petDiet = petDiet;
        this.petHealthAndAdaptation = petHealthAndAdaptation;
        this.changeBehavior = changeBehavior;
    }

    public Report(Long id, byte[] petPhoto, String petDiet, String petHealthAndAdaptation, String changeBehavior) {
        this.id = id;
        this.petPhoto = petPhoto;
        this.petDiet = petDiet;
        this.petHealthAndAdaptation = petHealthAndAdaptation;
        this.changeBehavior = changeBehavior;
    }

    public Report(Long id, byte[] petPhoto, String petDiet, String petHealthAndAdaptation, String changeBehavior, Date reportDate, Adopter adopter) {
        this.id = id;
        this.petPhoto = petPhoto;
        this.petDiet = petDiet;
        this.petHealthAndAdaptation = petHealthAndAdaptation;
        this.changeBehavior = changeBehavior;
        this.reportDate = reportDate;
        this.adopter = adopter;
    }

    public Report(byte[] petPhoto, String petDiet, String petHealthAndAdaptation, String changeBehavior, Adopter adopter) {
        this.petPhoto = petPhoto;
        this.petDiet = petDiet;
        this.petHealthAndAdaptation = petHealthAndAdaptation;
        this.changeBehavior = changeBehavior;
        this.adopter = adopter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(byte[] petPhoto) {
        this.petPhoto = petPhoto;
    }

    public String getPetDiet() {
        return petDiet;
    }

    public void setPetDiet(String petDiet) {
        this.petDiet = petDiet;
    }

    public String getPetHealthAndAdaptation() {
        return petHealthAndAdaptation;
    }

    public void setPetHealthAndAdaptation(String petHealthAndAdaptation) {
        this.petHealthAndAdaptation = petHealthAndAdaptation;
    }

    public String getChangeBehavior() {
        return changeBehavior;
    }

    public void setChangeBehavior(String changeBehavior) {
        this.changeBehavior = changeBehavior;
    }

    public Adopter getAdopter() {
        return adopter;
    }

    public void setAdopter(Adopter adopter) {
        this.adopter = adopter;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.deepEquals(petPhoto, report.petPhoto) && Objects.equals(petDiet, report.petDiet) && Objects.equals(petHealthAndAdaptation, report.petHealthAndAdaptation) && Objects.equals(changeBehavior, report.changeBehavior) && Objects.equals(reportDate, report.reportDate) && Objects.equals(adopter, report.adopter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Arrays.hashCode(petPhoto), petDiet, petHealthAndAdaptation, changeBehavior, reportDate, adopter);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", petPhoto=" + Arrays.toString(petPhoto) +
                ", petDiet='" + petDiet + '\'' +
                ", petHealthAndAdaptation='" + petHealthAndAdaptation + '\'' +
                ", changeBehavior='" + changeBehavior + '\'' +
                ", reportDate=" + reportDate +
                ", adopter=" + adopter +
                '}';
    }
}
