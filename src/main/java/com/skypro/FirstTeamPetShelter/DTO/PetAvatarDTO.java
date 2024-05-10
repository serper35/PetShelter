package com.skypro.FirstTeamPetShelter.DTO;

import java.util.Objects;

public class PetAvatarDTO {
    private Long id;
    private String mediaType;
    private long fileSize;
    private long petId;

    public PetAvatarDTO() {
    }

    public PetAvatarDTO(Long id, String mediaType, long fileSize, long petId) {
        this.id = id;
        this.mediaType = mediaType;
        this.fileSize = fileSize;
        this.petId = petId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetAvatarDTO that = (PetAvatarDTO) o;
        return fileSize == that.fileSize && petId == that.petId && Objects.equals(id, that.id) && Objects.equals(mediaType, that.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mediaType, fileSize, petId);
    }

    @Override
    public String toString() {
        return "petAvatarDTO{" +
                "id=" + id +
                ", mediaType='" + mediaType + '\'' +
                ", fileSize=" + fileSize +
                ", petId=" + petId +
                '}';
    }
}
