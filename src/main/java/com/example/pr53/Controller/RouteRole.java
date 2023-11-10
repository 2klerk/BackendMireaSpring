package com.example.pr53.Controller;

import com.example.pr53.CreateMessage;
import com.example.pr53.Entity.Client;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

//@Service
@RestController
public class RouteRole {
    ArrayList<String> TicketListAdmin = new ArrayList<>();
    ArrayList<String> TicketListSeller = new ArrayList<>();

    @PostMapping("Client/Role")
    public String roleTicket(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String Role
    ) {
        Client client = Route.clients.get(email);
        if (client != null) {
            if (Objects.equals(password, client.getPassword())) {
                return switch (Role) {
                    case "ADMIN" -> {
                        TicketListAdmin.add(email);
                        yield new JSONObject().put("message", "Ticket (role changing to admin) sent").toString();
                    }
                    case "SELLER" -> {
                        TicketListSeller.add(email);
                        yield new JSONObject().put("message", "Ticket (role changing to seller) sent").toString();
                    }
                    default -> new JSONObject().put("message", "Role not found").toString();
                };
            } else {
                return new JSONObject().put("message","Password wrong").toString();
            }
        } else return new JSONObject().put("message","Client not found").toString();
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

    @PutMapping("Client/Role")
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