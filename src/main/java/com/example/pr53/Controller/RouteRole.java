package com.example.pr53.Controller;

import com.example.pr53.CreateMessage;
import com.example.pr53.Entity.Client;
import com.example.pr53.JWT.JwtUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
public class RouteRole {
    private final ArrayList<String> ticketListAdmin = new ArrayList<>();
    private final ArrayList<String> ticketListSeller = new ArrayList<>();

    @PostMapping("/Client/Role")
    public String roleTicket(
            @RequestParam String token,
            @RequestParam String role
    ) {
        if (JwtUtils.validateToken(token)) {
            String email = JwtUtils.TokenList.get(token);
            Client client = Route.clients.get(email);
            if (client != null) {
                return switch (role.toUpperCase()) {
                    case "ADMIN" -> {
                        ticketListAdmin.add(email);
                        yield new JSONObject().put("message", "Ticket (role changing to admin) sent").toString();
                    }
                    case "SELLER" -> {
                        ticketListSeller.add(email);
                        yield new JSONObject().put("message", "Ticket (role changing to seller) sent").toString();
                    }
                    default -> new JSONObject().put("message", "Role not found").toString();
                };
            } else {
                return new JSONObject().put("message", "Client not found").toString();
            }
        } else {
            return new JSONObject().put("message", "Token not valid").toString();
        }
    }

    @GetMapping("/Client/Role")
    public String getRoleTicket(
            @RequestParam String email,
            @RequestParam String password
    ) {
        Client client = Route.clients.get(email);
        if (client != null) {
            if (Objects.equals(password, client.getPassword())) {
                if (Objects.equals(client.getRole(), "ADMIN")) {
                    return new JSONObject()
                            .put("Admin", ticketListAdmin)
                            .put("Seller", ticketListSeller).toString();
                } else {
                    return CreateMessage.permissionDenided();
                }
            } else {
                return CreateMessage.WrongPassword();
            }
        } else {
            return CreateMessage.ClientNotFound();
        }
    }

    @PutMapping("/Client/Role")
    public String setRole(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String userEmail,
            @RequestParam String role
    ) {
        Client client = Route.clients.get(email);
        if (client != null) {
            if (Objects.equals(password, client.getPassword())) {
                if (Objects.equals(client.getRole(), "ADMIN")) {
                    Client clientTicket = Route.clients.get(userEmail);
                    if (clientTicket != null) {
                        clientTicket.setRole(role);
                        return new JSONObject().put("message", "Role changed").toString();
                    } else {
                        return CreateMessage.ClientNotFound();
                    }
                } else {
                    return CreateMessage.permissionDenided();
                }
            } else {
                return CreateMessage.WrongPassword();
            }
        } else {
            return CreateMessage.ClientNotFound();
        }
    }
}