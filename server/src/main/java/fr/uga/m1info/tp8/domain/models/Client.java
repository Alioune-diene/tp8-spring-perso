package fr.uga.m1info.tp8.domain.models;

import lombok.Data;

import java.util.Set;

@Data
public class Client {
    private String name;
    private String email;
    private Set<Command> commands;
}
