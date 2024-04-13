package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String UserPhoneNumber;
    @OneToMany(mappedBy = "user")
    private Collection<Report> userReports;

    public User() {
    }

    public User(String userName, String userPhoneNumber) {
        this.userName = userName;
        UserPhoneNumber = userPhoneNumber;
    }

    public User(String userName, String userPhoneNumber, Collection<Report> userReports) {
        this.userName = userName;
        UserPhoneNumber = userPhoneNumber;
        this.userReports = userReports;
    }

    public User(Long id, String userName, String userPhoneNumber, Collection<Report> userReports) {
        this.id = id;
        this.userName = userName;
        UserPhoneNumber = userPhoneNumber;
        this.userReports = userReports;
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
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public Collection<Report> getUserReports() {
        return userReports;
    }

    public void setUserReports(Collection<Report> userReports) {
        this.userReports = userReports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(UserPhoneNumber, user.UserPhoneNumber) && Objects.equals(userReports, user.userReports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, UserPhoneNumber, userReports);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", UserPhoneNumber='" + UserPhoneNumber + '\'' +
                ", userReports=" + userReports +
                '}';
    }
}
