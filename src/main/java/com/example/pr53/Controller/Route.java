package com.example.pr53.Controller;

import com.example.pr53.CreateMessage;
import com.example.pr53.Entity.Client;
import com.example.pr53.JWT.JwtUtils;
import com.example.pr53.Market;
import org.apache.tomcat.util.http.parser.TokenList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Route {
    public static HashMap<String, Client> clients = new HashMap<>();
    public static Market market = new Market();

    @PostMapping("/Client")
    public String regUser(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name
    ) {
        if (!clients.containsKey(email)) {
            Client client = new Client(name, email, password);
            clients.put(email, client);
            String token = JwtUtils.generateToken(email);
            return CreateMessage.create("Client",token);
        }
        else return CreateMessage.alredCreated("Client");
    }

    @DeleteMapping("/Client")
    public String delUser(
            @RequestParam String email,
            @RequestParam String password
    ){
        if(clients.containsKey(email)){
            Client client=clients.get(email);
            if(checkData(email,password,client)){
                clients.remove(email);
                return CreateMessage.delete("Client");
            }
            else return CreateMessage.WrongPassword();
        }
        else return CreateMessage.ClientNotFound();
    }

    @GetMapping("/Client/signing")
    public String sign(
            @RequestParam String email,
            @RequestParam String password
    ) {
        if (clients.containsKey(email)){
            Client client = clients.get(email);
            if(checkData(email,password,client)){
                return new JSONObject()
                        .put("message","Auth Success")
                        .put("token", JwtUtils.generateToken(email))
                        .toString();
            }
            else return CreateMessage.WrongPassword();
        }
        else return CreateMessage.ClientNotFound();
    }

    @GetMapping("/Client")
    public String getClient(
            @RequestParam String token
//            @RequestParam String email,
//            @RequestParam String password
    ) {
        if (JwtUtils.validateToken(token)){
            String email = JwtUtils.TokenList.get(token);
            Client client = clients.get(email);
//            if(checkData(email,password,client)){
            return client.toString();
//            }else return CreateMessage.WrongPassword();
        }
        else return CreateMessage.ClientNotFound();
    }

    @GetMapping("/Client/getClients")
    public HashMap<String, Client> getClients() {
        return clients;
    }

    @PutMapping("/Client/Update/Name")
    public String updName(
//            @RequestParam String email,
//            @RequestParam String password,
            @RequestParam String token,
            @RequestParam String name
    ){
        if(JwtUtils.validateToken(token)){
            String email = JwtUtils.TokenList.get(token);
            Client client=clients.get(email);
            client.setName(name);
            return "Client name updated";
        }
        else return "Token not valid, signing again";
    }

    @PutMapping("/Client/Update/Password")
    public String updPassword(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String newPassword
    ){
        if(clients.containsKey(email)){
            Client client=clients.get(email);
            if(checkData(email,password,client)){
                client.setPassword(newPassword);
                return "Client password updated";
            }
            else return "Client password wrong";
        }
        else return "Client not found";
    }
    public static Boolean checkData(String email, String password, Client client){
        return client!=null && client.getEmail().equals(email) && client.getPassword().equals(password);
    }

    @PostMapping("Client/Cart/add")
    public String addToCart(
//            @RequestParam String email,
//            @RequestParam String password,
            @RequestParam String token,
            @RequestParam Integer article
    ){
        if (JwtUtils.validateToken(token)){
            String email = JwtUtils.TokenList.get(token);
            Client client = clients.get(email);
//            if(checkData(email,password,client)){
            client.addToCart(article);
            return "Product added";
//            }
//            else return CreateMessage.WrongPassword();
        }
        else return CreateMessage.ClientNotFound();
    }
    @GetMapping("Client/Cart/print")
    public String printCart(
            @RequestParam String token
    ){
        if (JwtUtils.validateToken(token)){
            String email = JwtUtils.TokenList.get(token);
            Client client = clients.get(email);
//            if(checkData(email,password,client)){
            client.printCart();
            return "Product printed";
//            }
//            else return CreateMessage.WrongPassword();
        }
        else return CreateMessage.ClientNotFound();
    }

    @GetMapping("Client/Cart/delete")
    public String delFromCart(
//            @RequestParam String email,
//            @RequestParam String password,
            @RequestParam String token,
            @RequestParam Integer select
    ){
        if (JwtUtils.validateToken(token)){
            String email = JwtUtils.TokenList.get(token);
            Client client = clients.get(email);
//            if(checkData(email,password,client)){
            client.deleteFromCart(select);
            return CreateMessage.delete("Product");
//            }
//            else return CreateMessage.WrongPassword();
        }
        else return CreateMessage.ClientNotFound();
    }



}