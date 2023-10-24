package com.example.projectSpring.student;

public class Validation {

    /*Checks whether the ID is correct.*/
    public static void checkValidId(Long id){
        int digitCount = String.valueOf(id).length();
        if (digitCount != 9){
            throw new IllegalArgumentException("The size of the ID is not correct.");
        }
    }

    /*Checks if the email address is correct.*/
    public static void isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
       if(!email.matches(emailRegex)){
           throw new IllegalArgumentException("The email: " + email + " is incorrect.");
       }
    }


}
