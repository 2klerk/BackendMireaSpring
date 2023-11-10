package com.example.pr53.Controller;

import com.example.pr53.JWT.JwtUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//@Service
@RestController
public class Auth {


    @GetMapping("/Client/CheckToken")
    public static String CheckToken(
            @RequestParam String token
    ){
        if(JwtUtils.validateToken(token)){
            return new JSONObject()
                    .put("message", "Valid")
                    .put("status", true).toString();
        }
        else{
            return new JSONObject()
                    .put("message", "not Valid")
                    .put("status", false).toString();
        }
    }

}
