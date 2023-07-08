package io.synansoft.userservice.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token ){
        return "Hello " + name + ",\n\nYour new account has been created. Please click the link below to verify your account" +
                "\n\n" + getVerificationUrl(host,token) + "\n\n The support team.";
    }
    public static String getVerificationUrl(String host, String token){
        return host + "/api/users?token=" + token;
    }
}
