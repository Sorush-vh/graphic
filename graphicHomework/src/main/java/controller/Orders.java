package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Orders {
    

    public static boolean isPasswordStrong(String password){

        if(password.length()<5) return false;

        Matcher numberMatcher=getMatcher("[0-9]", password);
        if(!numberMatcher.find()) return false;

        Matcher alphabetMatcher=getMatcher("[a-zA-Z]", password);
        if(!alphabetMatcher.find()) return false;

        return true;
    }

    public static Matcher getMatcher(String regex, String input){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(input);
        return matcher;
    }
}
