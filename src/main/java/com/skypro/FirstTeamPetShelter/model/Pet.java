package com.skypro.FirstTeamPetShelter.model;

import com.skypro.FirstTeamPetShelter.enums.PetHealth;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String petType;         // Тип животного (например, собака)
    private String petBreed;        // Порода животного (например, сиамская кошка)
    private String petGender;       // Пол животного
    private PetHealth petHealth;    // Здоровье животного
    private String petName;
    private int petAge;
    private String petDescription;  // Описание животного (например, рост в холке 25 см, цвет коричневый...)
    @OneToOne
    private PetAvatar petAvatar;
    @OneToOne
    private UserApp petPotentialOwner;
    @OneToOne
    private Adopter petOwner;

    public Pet() {
    }

    public Pet(String petType, String petBreed, String petGender, PetHealth petHealth, String petName, int petAge, String petDescription) {
        this.petType = petType;
        this.petBreed = petBreed;
        this.petGender = petGender;
        this.petHealth = petHealth;
        this.petName = petName;
        this.petAge = petAge;
        this.petDescription = petDescription;
    }

    public Pet(String petType, String petBreed, String petGender, PetHealth petHealth, String petName, int petAge, String petDescription, PetAvatar petAvatar) {
        this.petType = petType;
        this.petBreed = petBreed;
        this.petGender = petGender;
        this.petHealth = petHealth;
        this.petName = petName;
        this.petAge = petAge;
        this.petDescription = petDescription;
        this.petAvatar = petAvatar;
    }

    public Pet(Long id, String petType, String petBreed, String petGender, PetHealth petHealth, String petName, int petAge, String petDescription, PetAvatar petAvatar) {
        this.id = id;
        this.petType = petType;
        this.petBreed = petBreed;
        this.petGender = petGender;
        this.petHealth = petHealth;
        this.petName = petName;
        this.petAge = petAge;
        this.petDescription = petDescription;
        this.petAvatar = petAvatar;
    }

    public Pet(String petType, String petBreed, String petGender, PetHealth petHealth, String petName, int petAge, String petDescription, PetAvatar petAvatar, UserApp petPotentialOwner, Adopter petOwner) {
        this.petType = petType;
        this.petBreed = petBreed;
        this.petGender = petGender;
        this.petHealth = petHealth;
        this.petName = petName;
        this.petAge = petAge;
        this.petDescription = petDescription;
        this.petAvatar = petAvatar;
        this.petPotentialOwner = petPotentialOwner;
        this.petOwner = petOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public PetHealth getPetHealth() {
        return petHealth;
    }

    public void setPetHealth(PetHealth petHealth) {
        this.petHealth = petHealth;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public PetAvatar getPetAvatar() {
        return petAvatar;
    }

    public void setPetAvatar(PetAvatar petAvatar) {
        this.petAvatar = petAvatar;
    }

    public UserApp getPetPotentialOwner() {
        return petPotentialOwner;
    }

    public void setPetPotentialOwner(UserApp petPotentialOwner) {
        this.petPotentialOwner = petPotentialOwner;
    }

    public Adopter getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(Adopter petOwner) {
        this.petOwner = petOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return petAge == pet.petAge && Objects.equals(id, pet.id) && Objects.equals(petType, pet.petType) && Objects.equals(petBreed, pet.petBreed) && Objects.equals(petGender, pet.petGender) && petHealth == pet.petHealth && Objects.equals(petName, pet.petName) && Objects.equals(petDescription, pet.petDescription) && Objects.equals(petAvatar, pet.petAvatar) && Objects.equals(petPotentialOwner, pet.petPotentialOwner) && Objects.equals(petOwner, pet.petOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petType, petBreed, petGender, petHealth, petName, petAge, petDescription, petAvatar, petPotentialOwner, petOwner);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", petType='" + petType + '\'' +
                ", petBreed='" + petBreed + '\'' +
                ", petGender='" + petGender + '\'' +
                ", petHealth=" + petHealth +
                ", petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petDescription='" + petDescription + '\'' +
                ", petAvatar=" + petAvatar +
                ", petPotentialOwner=" + petPotentialOwner +
                ", petOwner=" + petOwner +
                '}';
    }
}
