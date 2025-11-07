package fr.uga.m1info.tp8.domain.models;

import lombok.Data;

import java.util.Collection;

@Data
public class Command {
    private Collection<OrderedProduct> orderedProducts;
}
