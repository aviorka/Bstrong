package com.example.aviorka.bstrong.DB_Managment.DataObjects;

import com.example.aviorka.bstrong.DB_Managment.Interfaces.DB_Data;

/*
* Trainee
* Get and set methods
*/
public class Trainee implements DB_Data {

    private long id;
    private String username;
    private String name;
    private String password;
    private String email;
    private String score;

    public Trainee() {

    }

    public Trainee(long id, String username, String name, String password, String email, String score) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }



    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
