package com.example.pr53.Controller;

import com.example.pr53.Entity.Client;
import com.example.pr53.Market;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Route {
    HashMap<String, Client> clients = new HashMap<>();
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
            return "Client created";
        }
        else return "Client already created";
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
                return "Client deleted";
            }
            else return "Client password wrong";
        }
        else return "Client not found";
    }

    @GetMapping("/Client")
    public String getClient(
            @RequestParam String email,
            @RequestParam String password
    ) {
        if (clients.containsKey(email)){
            Client client = clients.get(email);
            if(checkData(email,password,client)){
                return client.toString();
            }
            else return "Wrong Password";
        }
        else return "Clients nof found";
    }

    @GetMapping("/Client/getClients")
    public HashMap<String, Client> getClients() {
        return clients;
    }

    @PutMapping("/Client/Update/Name")
    public String updName(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name
    ){
        if(clients.containsKey(email)){
            Client client=clients.get(email);
            if(checkData(email,password,client)){
                client.setName(name);
                return "Client name updated";
            }
            else return "Client password wrong";
        }
        else return "Client not found";
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
    private Boolean checkData(String email,String password,Client client){
        return client!=null&&client.getEmail().equals(email) && client.getPassword().equals(password);
    }

    @PostMapping("Client/Cart/add")
    public String addToCart(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Integer article
    ){
        if (clients.containsKey(email)){
            Client client = clients.get(email);
            if(checkData(email,password,client)){
                client.addToCart(article);
                return "Product added";
            }
            else return "Wrong password";
        }
        else return "Client not found";
    }
    @GetMapping("Client/Cart/print")
    public String printCart(
            @RequestParam String email,
            @RequestParam String password
    ){
        if (clients.containsKey(email)){
            Client client = clients.get(email);
            if(checkData(email,password,client)){
                client.printCart();
                return "Product printed";
            }
            else return "Wrong password";
        }
        else return "Client not found";
    }

    @GetMapping("Client/Cart/delete")
    public String delFromCart(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Integer select
    ){
        if (clients.containsKey(email)){
            Client client = clients.get(email);
            if(checkData(email,password,client)){
                client.deleteFromCart(select);
                return "Product deleted";
            }
            else return "Wrong password";
        }
        else return "Client not found";
    }

}