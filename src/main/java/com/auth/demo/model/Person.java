package com.auth.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Person {

    @Id
    private String id;
    @Column(unique = true)
    private String idMicrosoft;
    private String firstname;
    private String lastname;
    private String occupation;
    private String color;
    private String welcomeMsg;
    @Indexed(unique = true)
    private String email;
    private String image;


    //Explicit constructor
    public Person() {
    }

    public Person(String id, String firstname, String lastname, String occupation, String color, String welcomeMsg, String email, String image) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.color = color;
        this.welcomeMsg = welcomeMsg;
        this.email = email;
        this.image = image;
    }

    public Person(String id, String idMicrosoft, String firstname, String lastname, String occupation, String color, String welcomeMsg, String email, String image) {
        this.id = id;
        this.idMicrosoft = idMicrosoft;
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.color = color;
        this.welcomeMsg = welcomeMsg;
        this.email = email;
        this.image = image;
    }

    public String getId() {
        if (this.id == null) {
            this.id = Integer.toHexString(this.hashCode());
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMicrosoft() {
        return idMicrosoft;
    }

    public void setIdMicrosoft(String idMicrosoft) {
        this.idMicrosoft = idMicrosoft;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName.replaceFirst(".", (firstName.charAt(0) + "").toUpperCase());
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName.replaceFirst(".", (lastName.charAt(0) + "").toUpperCase());
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getColor() {
//        String firstChar = "#";
//        String newColor = firstChar.concat(color);
//        return newColor;
        return color;
    }

    public void setColor(String color) {
        this.color = color;
//        this.color = color.substring(1, color.length());
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
