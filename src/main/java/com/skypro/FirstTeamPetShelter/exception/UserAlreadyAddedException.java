package com.skypro.FirstTeamPetShelter.exception;

public class UserAlreadyAddedException extends RuntimeException {
    public UserAlreadyAddedException(String message) {
        super(message);
    }
}
