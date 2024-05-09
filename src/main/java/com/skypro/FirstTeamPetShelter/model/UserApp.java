package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userPhoneNumber;
    private Long userTelegramId;
    private boolean isContacted;
    private boolean isBecomeAdoptive;   // Потенциальный усыновитель (false - нет, true - да)

    public UserApp() {
    }

    public UserApp(String userName, String userPhoneNumber) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public UserApp(String userName, String userPhoneNumber, Long userTelegramId) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userTelegramId = userTelegramId;
    }

    public UserApp(Long id, String userName, String userPhoneNumber, Long userTelegramId) {
        this.id = id;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userTelegramId = userTelegramId;
    }

    public UserApp(Long id, String userName, String userPhoneNumber, Long userTelegramId, boolean isContacted, boolean isBecomeAdoptive) {
        this.id = id;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userTelegramId = userTelegramId;
        this.isContacted = isContacted;
        this.isBecomeAdoptive = isBecomeAdoptive;
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

    public boolean isBecomeAdoptive() {
        return isBecomeAdoptive;
    }

    public void setBecomeAdoptive(boolean becomeAdoptive) {
        isBecomeAdoptive = becomeAdoptive;
    }

    public void setContacted(boolean contacted) {
        isContacted = contacted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserApp userApp = (UserApp) o;
        return isContacted == userApp.isContacted && isBecomeAdoptive == userApp.isBecomeAdoptive && Objects.equals(id, userApp.id) && Objects.equals(userName, userApp.userName) && Objects.equals(userPhoneNumber, userApp.userPhoneNumber) && Objects.equals(userTelegramId, userApp.userTelegramId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPhoneNumber, userTelegramId, isContacted, isBecomeAdoptive);
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userTelegramId=" + userTelegramId +
                ", isContacted=" + isContacted +
                ", isBecomeAdoptive=" + isBecomeAdoptive +
                '}';
    }
}
