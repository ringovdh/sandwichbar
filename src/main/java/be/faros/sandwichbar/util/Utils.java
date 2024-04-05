package be.faros.sandwichbar.util;

import java.util.regex.Pattern;

public class Utils {


    public static boolean validateEmail(String email) {
       String emailPattern = "^(.+)@(\\S+)$";
       return Pattern.compile(emailPattern)
               .matcher(email)
               .matches();
    }

    public static boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return Pattern.compile(passwordPattern)
                .matcher(password)
                .matches();
    }
}

