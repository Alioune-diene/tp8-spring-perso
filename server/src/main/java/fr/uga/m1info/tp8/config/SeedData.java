package fr.uga.m1info.tp8.config;

import fr.uga.m1info.tp8.models.*;
import fr.uga.m1info.tp8.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Profile("h2") // ne s’exécute que quand tu lances le profil h2
public class SeedData {

  @Bean
  CommandLineRunner seedH2(
      ProductEntityRepository productRepo,
      ClientEntityRepository clientRepo,
      CommandEntityRepository commandRepo,
      OrderedProductEntityRepository lineRepo
  ) {
    return args -> {
      // 1) Produits
      var pen       = productRepo.save(ProductEntity.builder().name("PEN").price(1.50).build());
      var book      = productRepo.save(ProductEntity.builder().name("BOOK").price(8.90).build());
      var notebook  = productRepo.save(ProductEntity.builder().name("NOTEBOOK").price(3.40).build());

      // 2) Clients
      var alice = clientRepo.save(ClientEntity.builder()
          .name("Alice").email("alice@example.org").build());
      var bob = clientRepo.save(ClientEntity.builder()
          .name("Bob").email("bob@example.org").build());

      // 3) Commandes (status 'NEW')
      var c1 = commandRepo.save(CommandEntity.builder()
          .date(new Date()).status("NEW").build());
      var c2 = commandRepo.save(CommandEntity.builder()
          .date(new Date()).status("NEW").build());

      // 4) Lignes de commande
      lineRepo.save(OrderedProductEntity.builder()
          .commandEntity(c1).productEntity(pen).quantity(2).build());
      lineRepo.save(OrderedProductEntity.builder()
          .commandEntity(c1).productEntity(book).quantity(1).build());
      lineRepo.save(OrderedProductEntity.builder()
          .commandEntity(c2).productEntity(notebook).quantity(3).build());

      // 5) Rattacher les commandes aux clients
      alice.setCommands(new HashSet<>(Set.of(c1)));
      bob.setCommands(new HashSet<>(Set.of(c2)));
      clientRepo.save(alice);
      clientRepo.save(bob);

      // À ce stade :
      // - Client 1 (Alice) a 1 commande (PEN x2, BOOK x1)
      // - Client 2 (Bob)   a 1 commande (NOTEBOOK x3)
    };
  }
}