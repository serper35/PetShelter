package com.skypro.FirstTeamPetShelter.exception;

public class PetAlreadyAddedException extends RuntimeException {
    public PetAlreadyAddedException(String message) {
        super(message);
    }
}
