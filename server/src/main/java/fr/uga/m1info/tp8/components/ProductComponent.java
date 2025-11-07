package fr.uga.m1info.tp8.components;
import fr.uga.m1info.tp8.exceptions.technical.ProductNotFoundException;
import fr.uga.m1info.tp8.repositories.ProductEntityRepository;
import fr.uga.m1info.tp8.requests.ProductRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ProductComponent {

    private final ProductEntityRepository productEntityRepository;

    public void verifyAllProductExist(Collection<ProductRequest> products) throws ProductNotFoundException {
        for (ProductRequest product : products) {
            productEntityRepository.findFirstByName(product.name())
                    .orElseThrow(() -> new ProductNotFoundException(String.format("le produit [%s] n'existe pas", product.name())));
        }
    }
}
