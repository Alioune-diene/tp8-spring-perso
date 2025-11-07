package fr.uga.m1info.tp8.requests;

import java.util.Collection;

public record CommandCreationRequest(
    Collection<ProductRequest> products
) {
}
