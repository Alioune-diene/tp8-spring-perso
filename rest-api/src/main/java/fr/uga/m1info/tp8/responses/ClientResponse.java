package fr.uga.m1info.tp8.responses;

import lombok.Data;

import java.util.Set;

@Data
public class ClientResponse {
    private String name;
    private String email;
    private Set<CommandResponse> commands;
}
