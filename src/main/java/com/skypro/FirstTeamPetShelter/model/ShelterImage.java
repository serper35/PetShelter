package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class ShelterImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] shelterAvatar;
    @Lob
    private byte[] shelterDrivingDirection;
    @OneToOne
    private Shelter shelter;

    public ShelterImage() {
    }

    public ShelterImage(byte[] shelterAvatar, byte[] shelterDrivingDirection, Shelter shelter) {
        this.shelterAvatar = shelterAvatar;
        this.shelterDrivingDirection = shelterDrivingDirection;
        this.shelter = shelter;
    }

    public ShelterImage(Long id, byte[] shelterAvatar, byte[] shelterDrivingDirection, Shelter shelter) {
        this.id = id;
        this.shelterAvatar = shelterAvatar;
        this.shelterDrivingDirection = shelterDrivingDirection;
        this.shelter = shelter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getShelterAvatar() {
        return shelterAvatar;
    }

    public void setShelterAvatar(byte[] shelterAvatar) {
        this.shelterAvatar = shelterAvatar;
    }

    public byte[] getShelterDrivingDirection() {
        return shelterDrivingDirection;
    }

    public void setShelterDrivingDirection(byte[] shelterDrivingDirection) {
        this.shelterDrivingDirection = shelterDrivingDirection;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterImage that = (ShelterImage) o;
        return Objects.equals(id, that.id) && Objects.deepEquals(shelterAvatar, that.shelterAvatar) && Objects.deepEquals(shelterDrivingDirection, that.shelterDrivingDirection) && Objects.equals(shelter, that.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Arrays.hashCode(shelterAvatar), Arrays.hashCode(shelterDrivingDirection), shelter);
    }

    @Override
    public String toString() {
        return "ShelterImage{" +
                "id=" + id +
                ", shelterAvatar=" + Arrays.toString(shelterAvatar) +
                ", shelterDrivingDirection=" + Arrays.toString(shelterDrivingDirection) +
                ", shelter=" + shelter +
                '}';
    }
}
