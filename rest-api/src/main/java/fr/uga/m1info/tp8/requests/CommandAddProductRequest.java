package fr.uga.m1info.tp8.requests;

import java.util.Set;

public record CommandAddProductRequest(
        Set<ProductRequest> productsToAdd
) {
}
