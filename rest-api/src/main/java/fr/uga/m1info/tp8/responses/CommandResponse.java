package fr.uga.m1info.tp8.responses;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class CommandResponse {
    private Collection<OrderedProductResponse> orderedProducts;
}
