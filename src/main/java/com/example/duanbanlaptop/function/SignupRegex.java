package com.example.duanbanlaptop.function;

public class SignupRegex {

    public static boolean validPhoneNumber(String phoneNumber) {
        String regex = "0\\d{9,10}";
        return phoneNumber.matches(regex);
    }

    public static boolean validEmail(String email) {
        String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regexEmail);
    }

    public static boolean validUsername(String username) {
        String regexUsername = "^[a-zA-Z0-9]+$";
        return username.matches(regexUsername);
    }
}
