package com.skypro.FirstTeamPetShelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String keyWord;
    private String message;

    public Info() {
    }

    public Info(String message) {
        this.message = message;
    }

    public Info(String keyWord, String message) {
        this.keyWord = keyWord;
        this.message = message;
    }

    public Info(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(id, info.id) && Objects.equals(keyWord, info.keyWord) && Objects.equals(message, info.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyWord, message);
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", keyWord='" + keyWord + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
