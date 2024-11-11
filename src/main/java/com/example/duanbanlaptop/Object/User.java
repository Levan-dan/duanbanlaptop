package com.example.duanbanlaptop.Object;

public class User {
    private int idUser;
    private String username;
    private String parword;
    private String address;
    private String phoneNumber;
    private String role;
    private String email;
    private String gender;

    public User(int idUser, String username, String parword, String address, String phoneNumber, String role, String email, String gender) {
        this.idUser = idUser;
        this.username = username;
        this.parword = parword;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.email = email;
        this.gender = gender;
    }

    public User() {
    }
    public User(String username, String address, String phoneNumber,String email){
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParword() {
        return parword;
    }

    public void setParword(String parword) {
        this.parword = parword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
