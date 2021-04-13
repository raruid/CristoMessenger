package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class User {

    private String id_user;
    private String name;
    private String password;
    private String surname1;
    private String surname2;
    private String photo;
    private boolean state;

    public User() {
        this.id_user = "NULL";
        this.name = "NULL";
        this.password = "NULL";
        this.surname1 = "NULL";
        this.surname2 = "NULL";
        this.photo = "NULL";
        this.state = false;
    }

    public User(String id_user, String name, String password, String surname1, String surname2, String photo, boolean state) {
        this.id_user = id_user;
        this.name = name;
        this.password = password;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.photo = photo;
        this.state = state;
    }

    //SETS
    
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    //GETS
    
    public String getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public String getPhoto() {
        return photo;
    }

    public boolean getState() {
        return state;
    }

/*    
    public void getUser(){
    }
*/        
}
