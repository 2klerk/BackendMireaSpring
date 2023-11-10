package com.example.pr53;

import com.example.pr53.JWT.JwtUtils;
import org.json.JSONObject;

public class CreateMessage {
    private static JSONObject json = new JSONObject();
    public static String ClientNotFound(){
        json.put("message", " Client not found");
        return json.toString();
    }
    public static String WrongPassword(){
        json.put("message", " Wrong password");
        return json.toString();
    }
    public static String create(String obj, String token){
        json.put("message", obj+" created");
        json.put("token", token);
        return json.toString();
    }
    public static String createObject(String obj){
        json.put("message", obj+" created");
        return json.toString();
    }
    public static String update(String obj){
        json.put("message", obj+" updated");
        return json.toString();
    }
    public static String delete(String obj){
        json.put("message", obj+" deleted");
        return json.toString();
    }

    public static String alredCreated(String obj){
        json.put("message", obj+" already created");
        return json.toString();
    }

    public static String permissionDenided(){
        json.put("message", "Permission denied");
        return json.toString();
    }
}
