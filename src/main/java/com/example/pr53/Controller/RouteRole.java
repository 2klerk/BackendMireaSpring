package com.example.pr53.Controller;

import com.example.pr53.CreateMessage;
import com.example.pr53.Entity.Client;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@RestController
public class RouteRole {
    ArrayList<String> TicketListAdmin = new ArrayList<>();
    ArrayList<String> TicketListSeller = new ArrayList<>();

    @PutMapping("Client/Role")
    public String roleTicket(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Integer Role
    ) {
        Client client = Route.clients.get(email);
        if (client != null) {
            if (Objects.equals(password, client.getPassword())) {
                switch (Role) {
                    case 0:
                        TicketListAdmin.add(email);
                        return "Ticket (role changing to admin) sent";
                    case 1:
                        TicketListSeller.add(email);
                        return "Ticket (role changing to seller) sent";
                    default:
                        return "Role not found";
                }
            } else {
                return "Password wrong";
            }
        } else return "Client not found";
    }

    @GetMapping("Client/Role")
    public String roleTicket(
            @RequestParam String email,
            @RequestParam String password
    ) {
        Client client = Route.clients.get(email);
        if (client != null) {
            if (Objects.equals(password, client.getPassword())) {
                if(Objects.equals(client.getRole(), "ADMIN")){
                    return new JSONObject()
                            .put("Admin", TicketListAdmin)
                            .put("Seller", TicketListSeller ).toString();
                }
                else return CreateMessage.permissionDenided();
            } else {
                return CreateMessage.WrongPassword();
            }
        } else return CreateMessage.ClientNotFound();
    }

    @PostMapping("Client/Role")
    public String roleSet(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String userEmail,
            @RequestParam String Role
    ) {
        Client client = Route.clients.get(email);
        if (client != null) {
            if (Objects.equals(password, client.getPassword())) {
                if(Objects.equals(client.getRole(), "ADMIN")){
                    Client clientTicket = Route.clients.get(userEmail);
                    clientTicket.setRole(Role);
                    return new JSONObject().put("message","Role changed").toString();
                }
                else return CreateMessage.permissionDenided();
            } else {
                return CreateMessage.WrongPassword();
            }
        } else return CreateMessage.ClientNotFound();
    }
}