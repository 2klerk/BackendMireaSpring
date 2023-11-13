package com.example.pr53.Controller;

import com.example.pr53.Entity.Client;
import com.example.pr53.JWT.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteOrder {
//    @DeleteMapping
//    public static String getOrder(
//            @RequestParam String token
//    ){
//        if(JwtUtils.validateToken(token)){
//
//        }
//        else{}
//    }
    @PostMapping("Client/Cart/createOrder")
    public static String createOrder(
            @RequestParam String token,
            @RequestParam Integer select
    ){
        if (JwtUtils.validateToken(token)) {
            String email = JwtUtils.TokenList.get(token);
            Client client = Route.clients.get(email);
                client.createOrder(select);
                return new JSONObject().put("message","create order").toString();
            }
        else return new JSONObject().put("message","token not valid").toString();
    }

}
