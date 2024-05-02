package com.skypro.FirstTeamPetShelter.exception;

public class AdopterNotFoundException extends RuntimeException {
    public AdopterNotFoundException(String message) {
        super(message);
    }
}
