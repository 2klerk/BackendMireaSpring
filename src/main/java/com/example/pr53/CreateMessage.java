package com.example.pr53;

import com.example.pr53.JWT.JwtUtils;
import org.json.JSONObject;

public class CreateMessage {
    public static String ClientNotFound(){
        return new JSONObject().put("message", " Client not found").toString();
    }
    public static String WrongPassword(){
        return new JSONObject().put("message", " Wrong password").toString();
    }
    public static String create(String obj, String token){
        return new JSONObject()
                .put("message", obj+" created")
                .put("token", token).toString();
    }
    public static String createObject(String obj){
        return new JSONObject()
                .put("message", obj+" created").toString();
    }
    public static String update(String obj){
        return new JSONObject()
                .put("message", obj+" updated").toString();
    }
    public static String delete(String obj){
        return new JSONObject()
                .put("message", obj+" deleted").toString();
    }

    public static String alredCreated(String obj){
        return new JSONObject()
                .put("message", obj+" already created").toString();
    }

    public static String permissionDenided(){
        return new JSONObject()
                .put("message", "Permission denied").toString();
    }
}
