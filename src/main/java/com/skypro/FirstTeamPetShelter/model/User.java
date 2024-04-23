package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userPhoneNumber;
    private Long userTelegramId;
    private boolean isContacted;

    public User() {
    }

    public User(String userName, String userPhoneNumber) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public User(String userName, String userPhoneNumber, Long userTelegramId) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userTelegramId = userTelegramId;
    }

    public User(Long id, String userName, String userPhoneNumber, Long userTelegramId) {
        this.id = id;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userTelegramId = userTelegramId;
    }

    public User(Long id, String userName, String userPhoneNumber, Long userTelegramId, boolean isContacted) {
        this.id = id;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userTelegramId = userTelegramId;
        this.isContacted = isContacted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public void setUserTelegramId(Long userTelegramId) {
        this.userTelegramId = userTelegramId;
    }

    public boolean isContacted() {
        return isContacted;
    }

    public void setContacted(boolean contacted) {
        isContacted = contacted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isContacted == user.isContacted && Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(userPhoneNumber, user.userPhoneNumber) && Objects.equals(userTelegramId, user.userTelegramId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPhoneNumber, userTelegramId, isContacted);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userTelegramId=" + userTelegramId +
                ", isContacted=" + isContacted +
                '}';
    }
}
