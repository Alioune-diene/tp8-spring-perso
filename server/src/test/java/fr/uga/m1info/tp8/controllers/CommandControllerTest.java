package fr.uga.m1info.tp8.controllers;

import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.models.OrderedProductEntity;
import fr.uga.m1info.tp8.models.ProductEntity;
import fr.uga.m1info.tp8.repositories.CommandEntityRepository;
import fr.uga.m1info.tp8.repositories.OrderedProductEntityRepository;
import fr.uga.m1info.tp8.repositories.ProductEntityRepository;
import fr.uga.m1info.tp8.requests.CommandAddProductRequest;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;
import fr.uga.m1info.tp8.requests.CommandRemoveProductsRequest;
import fr.uga.m1info.tp8.requests.ProductRequest;
import fr.uga.m1info.tp8.responses.CommandResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommandControllerTest {
    private static final String BASE_URI = "/api/command";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private CommandEntityRepository commandEntityRepository;
    @Autowired
    private ProductEntityRepository productEntityRepository;
    @Autowired
    private OrderedProductEntityRepository orderedProductEntityRepository;

    @BeforeEach
    void setUp() {
        commandEntityRepository.deleteAll();
        productEntityRepository.deleteAll();
    }

    @Test
    @DisplayName("POST-201 création d'une commande avec au moins 1 produits")
    void create_command() {
        ProductEntity productEntity = ProductEntity
                .builder()
                .name("test")
                .price(10.0)
                .build();
        productEntityRepository.save(productEntity);

        RequestEntity<CommandCreationRequest> request = RequestEntity
                .post(BASE_URI)
                .body(new CommandCreationRequest(
                        Set.of(new ProductRequest(
                                "test", 3
                        ))
                ));
        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(exchange.getBody()).isNotNull();

    }

    @Test
    @DisplayName("POST-400 création d'une commande aucun produits")
    void create_command_without_product() {
        RequestEntity<CommandCreationRequest> request = RequestEntity
                .post(BASE_URI)
                .body(new CommandCreationRequest(Set.of()));

        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("POST-404 création d'une commande avec un produit qui n'existe plus")
    void create_command_with_product_not_exist() {
        RequestEntity<CommandCreationRequest> request = RequestEntity
                .post(BASE_URI)
                .body(new CommandCreationRequest(
                        Set.of(new ProductRequest(
                                "test", 3
                        ))
                ));
        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("PUT-200 Ajout d'un produit à une commande")
    void add_product() {
        CommandEntity commandEntity = CommandEntity
                .builder()
                .orderedProductEntities(Set.of())
                .status("OK")
                .date(Date.from(Instant.now()))
                .build();
        commandEntityRepository.save(commandEntity);
        ProductEntity productEntity = ProductEntity
                .builder()
                .name("test")
                .price(10.0)
                .build();
        productEntityRepository.save(productEntity);

        RequestEntity<CommandAddProductRequest> request = RequestEntity
                .put(BASE_URI + "/" + commandEntity.getId() + "/products/add")
                .body(new CommandAddProductRequest(
                        Set.of(new ProductRequest(
                                "test", 3
                        ))
                ));

        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).isNotNull();
    }

    @Test
    @DisplayName("PUT-404 Ajout d'un produit qui n'existe pas à une commande")
    void add_product_not_exist() {
        CommandEntity commandEntity = CommandEntity
                .builder()
                .orderedProductEntities(Set.of())
                .status("OK")
                .date(Date.from(Instant.now()))
                .build();
        commandEntityRepository.save(commandEntity);
        RequestEntity<CommandAddProductRequest> request = RequestEntity
                .put(BASE_URI + "/" + commandEntity.getId() + "/products/")
                .body(new CommandAddProductRequest(
                        Set.of(new ProductRequest(
                                "test", 3
                        ))
                ));


        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    @DisplayName("PUT-404 Ajout d'un produit à une commande qui n'existe pas")
    void add_product_command_not_exist() {
        RequestEntity<CommandAddProductRequest> request = RequestEntity
                .put(BASE_URI + "1/products/add")
                .body(new CommandAddProductRequest(
                        Set.of(new ProductRequest(
                                "test", 1
                        ))
                ));
        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    @DisplayName("PUT-200 Suppression d'un produit à une commande")
    void remove_product() {
        CommandEntity commandEntity = CommandEntity
                .builder()
                .orderedProductEntities(Set.of())
                .status("OK")
                .date(Date.from(Instant.now()))
                .build();
        commandEntityRepository.save(commandEntity);

        ProductEntity productEntity = ProductEntity
                .builder()
                .name("test")
                .price(10.0)
                .build();
        productEntityRepository.save(productEntity);

        OrderedProductEntity orderedProductEntity1 = OrderedProductEntity
                .builder()
                .commandEntity(commandEntity)
                .quantity(1)
                .productEntity(productEntity)
                .build();
        orderedProductEntityRepository.save(orderedProductEntity1);


        OrderedProductEntity orderedProductEntity2 = OrderedProductEntity
                .builder()
                .commandEntity(commandEntity)
                .quantity(3)
                .productEntity(productEntity)
                .build();
        orderedProductEntityRepository.save(orderedProductEntity2);


        commandEntity.setOrderedProductEntities(Set.of(orderedProductEntity1, orderedProductEntity2));
        commandEntityRepository.save(commandEntity);

        RequestEntity<CommandRemoveProductsRequest> request = RequestEntity
                .put(BASE_URI + "/" + commandEntity.getId() + "/products/remove")
                .body(new CommandRemoveProductsRequest(
                        Set.of(orderedProductEntity1.getId())
                ));
        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Disabled
    @DisplayName("PUT-400 Suppression d'un produit qui n'est pas dans la commande")
    void remove_product_not_exist() {
        CommandEntity commandEntity = CommandEntity
                .builder()
                .orderedProductEntities(Set.of())
                .status("OK")
                .date(Date.from(Instant.now()))
                .build();
        commandEntityRepository.save(commandEntity);
        RequestEntity<CommandRemoveProductsRequest> request = RequestEntity
                .put(BASE_URI + "/" + commandEntity.getId() + "/products/remove")
                .body(new CommandRemoveProductsRequest(
                        Set.of(1000L)
                ));
        ResponseEntity<CommandResponse> exchange =
                testRestTemplate.exchange(
                        request,
                        CommandResponse.class
                );
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("PUT-404 Suppression d'un produit à une commande qui n'existe pas")
    void remove_product_command_not_exist() {
        RequestEntity<CommandRemoveProductsRequest> request = RequestEntity
                .put(BASE_URI + "1/products/remove")
                .body(new CommandRemoveProductsRequest(
                        Set.of(1L, 2L, 3L)
                ));

        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("PUT-400 Suppression d'un produit à une commande qui à un seul produits")
    void remove_product_command_without_product() {
        CommandEntity commandEntity = CommandEntity
                .builder()
                .orderedProductEntities(Set.of())
                .status("OK")
                .date(Date.from(Instant.now()))
                .build();
        commandEntityRepository.save(commandEntity);

        ProductEntity productEntity = ProductEntity
                .builder()
                .name("test")
                .price(10.0)
                .build();
        productEntityRepository.save(productEntity);

        OrderedProductEntity orderedProductEntity1 = OrderedProductEntity
                .builder()
                .commandEntity(commandEntity)
                .quantity(1)
                .productEntity(productEntity)
                .build();
        orderedProductEntityRepository.save(orderedProductEntity1);

        commandEntity.setOrderedProductEntities(Set.of(orderedProductEntity1));
        commandEntityRepository.save(commandEntity);

        RequestEntity<CommandRemoveProductsRequest> request = RequestEntity
                .put(BASE_URI + "/" + commandEntity.getId() + "/products/remove")
                .body(new CommandRemoveProductsRequest(
                        Set.of(orderedProductEntity1.getId())
                ));
        ResponseEntity<CommandResponse> exchange = testRestTemplate.exchange(request, CommandResponse.class);
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
