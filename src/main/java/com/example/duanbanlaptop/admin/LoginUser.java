package com.example.duanbanlaptop.admin;

public class LoginUser {
    private int id;
    private String name;
    private static LoginUser instance;

    public LoginUser(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public static void login(int id, String name){
        if(instance == null){
            instance = new LoginUser(id, name);
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static LoginUser getInstance() {
        return instance;
    }

    public static void setInstance(LoginUser instance) {
        LoginUser.instance = instance;
    }


    public static void logout(){
        instance = null;
    }
}
