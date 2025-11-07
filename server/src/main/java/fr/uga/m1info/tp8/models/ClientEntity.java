package fr.uga.m1info.tp8.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Email
    private String email;

    @OneToMany
    @JoinColumn(name="client_id", referencedColumnName="id")
    private Set<CommandEntity> commands;
}
