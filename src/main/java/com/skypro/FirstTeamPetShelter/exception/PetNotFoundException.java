package com.skypro.FirstTeamPetShelter.exception;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String message) {
        super(message);
    }
}
