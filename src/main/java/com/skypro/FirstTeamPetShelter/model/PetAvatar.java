package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class PetAvatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] smallAvatar;
    @OneToOne
    private Pet pet;

    public PetAvatar() {
    }

    public PetAvatar(String filePath, long fileSize, String mediaType, byte[] smallAvatar, Pet pet) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.smallAvatar = smallAvatar;
        this.pet = pet;
    }

    public PetAvatar(Long id, String filePath, long fileSize, String mediaType, byte[] smallAvatar, Pet pet) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.smallAvatar = smallAvatar;
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getSmallAvatar() {
        return smallAvatar;
    }

    public void setSmallAvatar(byte[] smallAvatar) {
        this.smallAvatar = smallAvatar;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetAvatar petAvatar = (PetAvatar) o;
        return fileSize == petAvatar.fileSize && Objects.equals(id, petAvatar.id) && Objects.equals(filePath, petAvatar.filePath) && Objects.equals(mediaType, petAvatar.mediaType) && Objects.deepEquals(smallAvatar, petAvatar.smallAvatar) && Objects.equals(pet, petAvatar.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, Arrays.hashCode(smallAvatar), pet);
    }

    @Override
    public String toString() {
        return "PetAvatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", smallAvatar=" + Arrays.toString(smallAvatar) +
                ", pet=" + pet +
                '}';
    }
}
