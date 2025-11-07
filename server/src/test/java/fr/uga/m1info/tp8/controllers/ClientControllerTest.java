package fr.uga.m1info.tp8.controllers;

import fr.uga.m1info.tp8.errors.ErrorResponse;
import fr.uga.m1info.tp8.models.ClientEntity;
import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.repositories.ClientEntityRepository;
import fr.uga.m1info.tp8.repositories.CommandEntityRepository;
import fr.uga.m1info.tp8.requests.ClientCreationRequest;
import fr.uga.m1info.tp8.responses.ClientResponse;
import fr.uga.m1info.tp8.responses.CommandResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerTest {
    private static final String BASE_URI = "/api/client";

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private CommandEntityRepository commandEntityRepository;
    @Autowired
    private ClientEntityRepository clientEntityRepository;


    @BeforeEach
    void setUp() {
        clientEntityRepository.deleteAll();
        commandEntityRepository.deleteAll();
    }

    @Test
    @DisplayName("POST-201 Création d'un client")
    void creation_client_201() {
        RequestEntity<ClientCreationRequest> request = RequestEntity
                .post(BASE_URI)
                .body(new ClientCreationRequest(
                        "test",
                        "test@example.com"
                ));
        ResponseEntity<ClientResponse> objectResponseEntity = testRestTemplate.exchange(request, ClientResponse.class);
        assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("POST-400 L'adresse émail n'est pas du bon format")
    void creation_client_400() {
        RequestEntity<ClientCreationRequest> request = RequestEntity
                .post(BASE_URI)
                .body(new ClientCreationRequest(
                        "test",
                        "example.com"
                ));

        ResponseEntity<ClientResponse> objectResponseEntity = testRestTemplate.exchange(request, ClientResponse.class);
        assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    @DisplayName("Get-200 récupération des commandes d'un client qui n'a pas de commande")
    void get_commands_200_empty() {
        ClientEntity client = ClientEntity
                .builder()
                .email("test@example.com")
                .name("test")
                .commands(Set.of())
                .build();
        clientEntityRepository.save(client);

        ResponseEntity<Set<CommandResponse>> exchange = testRestTemplate.exchange(BASE_URI + "/" + client.getId() + "/commands",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).isEmpty();
    }

    @Test
    @DisplayName("Get-200 récupération des commandes d'un client qui a des commandes")
    void get_commands_200() {
        CommandEntity commandEntity = CommandEntity
                .builder()
                .orderedProductEntities(Set.of())
                .status("OK")
                .date(Date.from(Instant.now()))
                .build();
        commandEntityRepository.save(commandEntity);
        ClientEntity client = ClientEntity.builder()
                .email("test@example.com")
                .name("test")
                .commands(Set.of(commandEntity))
                .build();
        clientEntityRepository.save(client);

        ResponseEntity<Collection<CommandResponse>> exchange = testRestTemplate.exchange(
                BASE_URI +"/"+ client.getId() +"/commands",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).isNotEmpty();
    }

    @Test
    @DisplayName("Get-404 récupération des commandes d'un client qui n'existe pas")
    void get_commands_404() {
        ResponseEntity<ErrorResponse> exchange = testRestTemplate.exchange(
                BASE_URI +"/1/commands",
                HttpMethod.GET,
                null,
                ErrorResponse.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
