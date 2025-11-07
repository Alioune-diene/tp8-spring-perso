package fr.uga.m1info.tp8.services;

import fr.uga.m1info.tp8.components.CommandComponent;
import fr.uga.m1info.tp8.components.ProductComponent;
import fr.uga.m1info.tp8.domain.models.Command;
import fr.uga.m1info.tp8.exceptions.rest.CommandNotFoundRestException;
import fr.uga.m1info.tp8.exceptions.rest.EmptyCommandRestException;
import fr.uga.m1info.tp8.exceptions.rest.ProductNotFoundRestException;
import fr.uga.m1info.tp8.exceptions.rest.ProductNotInCommandRestException;
import fr.uga.m1info.tp8.exceptions.technical.CommandEntityNotFoundException;
import fr.uga.m1info.tp8.exceptions.technical.ProductNotFoundException;
import fr.uga.m1info.tp8.mappers.CommandMapper;
import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.models.OrderedProductEntity;
import fr.uga.m1info.tp8.models.ProductEntity;
import fr.uga.m1info.tp8.repositories.CommandEntityRepository;
import fr.uga.m1info.tp8.repositories.ProductEntityRepository;
import fr.uga.m1info.tp8.requests.CommandAddProductRequest;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;
import fr.uga.m1info.tp8.requests.CommandRemoveProductsRequest;
import fr.uga.m1info.tp8.requests.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final CommandEntityRepository commandEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    private final CommandMapper commandMapper;
    private final CommandComponent commandComponent;
    private final ProductComponent productComponent;

    @Transactional
    public Command createCommand(CommandCreationRequest request) {
        // Validation: au moins 1 produit
        if (request.products() == null || request.products().isEmpty()) {
            throw new EmptyCommandRestException("Une commande doit avoir au moins 1 produit");
        }

        // Validation: tous les produits doivent exister
        try {
            productComponent.verifyAllProductExist(request.products());
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundRestException(e.getMessage());
        }

        // Créer l'entité commande
        CommandEntity commandEntity = CommandEntity.builder()
                .date(new Date())
                .status("CREATED")
                .orderedProductEntities(new HashSet<>())
                .build();

        // Ajouter les produits à la commande
        for (ProductRequest productRequest : request.products()) {
            ProductEntity productEntity = productEntityRepository.findFirstByName(productRequest.name())
                    .orElseThrow(() -> new ProductNotFoundRestException(
                            String.format("Le produit [%s] n'existe pas", productRequest.name())));

            OrderedProductEntity orderedProduct = OrderedProductEntity.builder()
                    .quantity(productRequest.quantity())
                    .productEntity(productEntity)
                    .commandEntity(commandEntity)
                    .build();

            commandEntity.getOrderedProductEntities().add(orderedProduct);
        }

        // Sauvegarder la commande
        CommandEntity savedCommand = commandEntityRepository.save(commandEntity);
        return commandMapper.toCommand(savedCommand);
    }

    @Transactional
    public Command addProducts(Long idCommand, CommandAddProductRequest request) {
        // Validation: la commande doit exister
        CommandEntity commandEntity;
        try {
            commandEntity = commandComponent.getCommandEntityById(idCommand);
        } catch (CommandEntityNotFoundException e) {
            throw new CommandNotFoundRestException(e.getMessage());
        }

        // Validation: les produits doivent exister
        try {
            productComponent.verifyAllProductExist(request.productsToAdd());
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundRestException(e.getMessage());
        }

        // Ajouter les produits à la commande
        for (ProductRequest productRequest : request.productsToAdd()) {
            ProductEntity productEntity = productEntityRepository.findFirstByName(productRequest.name())
                    .orElseThrow(() -> new ProductNotFoundRestException(
                            String.format("Le produit [%s] n'existe pas", productRequest.name())));

            // Vérifier si le produit est déjà dans la commande
            boolean productExists = commandEntity.getOrderedProductEntities().stream()
                    .anyMatch(op -> op.getProductEntity().getName().equals(productRequest.name()));

            if (productExists) {
                // Mettre à jour la quantité
                commandEntity.getOrderedProductEntities().stream()
                        .filter(op -> op.getProductEntity().getName().equals(productRequest.name()))
                        .findFirst()
                        .ifPresent(op -> op.setQuantity(op.getQuantity() + productRequest.quantity()));
            } else {
                // Ajouter un nouveau produit
                OrderedProductEntity orderedProduct = OrderedProductEntity.builder()
                        .quantity(productRequest.quantity())
                        .productEntity(productEntity)
                        .commandEntity(commandEntity)
                        .build();

                commandEntity.getOrderedProductEntities().add(orderedProduct);
            }
        }

        // Sauvegarder la commande
        CommandEntity savedCommand = commandEntityRepository.save(commandEntity);
        return commandMapper.toCommand(savedCommand);
    }

    @Transactional
    public Command removeProducts(Long idCommand, CommandRemoveProductsRequest request) {
        // Validation: la commande doit exister
        CommandEntity commandEntity;
        try {
            commandEntity = commandComponent.getCommandEntityById(idCommand);
        } catch (CommandEntityNotFoundException e) {
            throw new CommandNotFoundRestException(e.getMessage());
        }

        // Validation: la commande ne doit pas être vide après suppression
        if (commandEntity.getOrderedProductEntities().size() <= request.productsToRemove().size()) {
            throw new EmptyCommandRestException("La commande ne peut pas être vide après la suppression");
        }

        // Validation: les produits doivent être dans la commande
        Set<Long> orderedProductIds = new HashSet<>();
        for (OrderedProductEntity op : commandEntity.getOrderedProductEntities()) {
            orderedProductIds.add(op.getId());
        }

        for (Long productId : request.productsToRemove()) {
            if (!orderedProductIds.contains(productId)) {
                throw new ProductNotInCommandRestException(
                        String.format("Le produit [%s] n'est pas dans la commande", productId));
            }
        }

        // Supprimer les produits
        commandEntity.getOrderedProductEntities().removeIf(
                op -> request.productsToRemove().contains(op.getId()));

        // Sauvegarder la commande
        CommandEntity savedCommand = commandEntityRepository.save(commandEntity);
        return commandMapper.toCommand(savedCommand);
    }
}
