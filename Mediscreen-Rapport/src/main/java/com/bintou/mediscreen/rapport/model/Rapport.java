package com.bintou.mediscreen.rapport.model;

import java.io.Serializable;

public class Rapport implements Serializable {

    private String firstName;

    private String lastName;

    private String gender;

    private Long age;

    private Status status;


    public Rapport() {
    }

    public Rapport(String firstName, String lastName, String gender, Long age, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssessment(Status status) {
        this.status = status;
    }

}
