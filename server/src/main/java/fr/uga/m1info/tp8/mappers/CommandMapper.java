package fr.uga.m1info.tp8.mappers;

import fr.uga.m1info.tp8.domain.models.Command;
import fr.uga.m1info.tp8.domain.models.OrderedProduct;
import fr.uga.m1info.tp8.responses.CommandResponse;
import fr.uga.m1info.tp8.responses.OrderedProductResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.models.OrderedProductEntity;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;

import java.util.Set;

@Mapper
public interface CommandMapper {

   CommandResponse toResponse(Command command);

    CommandEntity toEntity(CommandCreationRequest commandCreationRequest);


    @Mapping(target = "orderedProducts",source = "orderedProductEntities")
    Command toCommand(CommandEntity commandEntity);

    @Mapping(target = "nameProduct", source = "productEntity.name")
    OrderedProduct toOrderedProduct(OrderedProductEntity orderedProductEntity);

    OrderedProductResponse toResponse(OrderedProduct orderedProduct);

    Set<CommandResponse> toResponses(Set<Command> commands);


}
