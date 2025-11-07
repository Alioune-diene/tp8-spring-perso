package fr.uga.m1info.tp8.mappers;

import fr.uga.m1info.tp8.domain.models.Command;
import fr.uga.m1info.tp8.domain.models.OrderedProduct;
import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.models.OrderedProductEntity;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;
import fr.uga.m1info.tp8.responses.CommandResponse;
import fr.uga.m1info.tp8.responses.OrderedProductResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-07T22:51:51+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.9 (Debian)"
)
@Component
public class CommandMapperImpl implements CommandMapper {

    @Override
    public CommandResponse toResponse(Command command) {
        if ( command == null ) {
            return null;
        }

        CommandResponse.CommandResponseBuilder commandResponse = CommandResponse.builder();

        commandResponse.orderedProducts( orderedProductCollectionToOrderedProductResponseCollection( command.getOrderedProducts() ) );

        return commandResponse.build();
    }

    @Override
    public CommandEntity toEntity(CommandCreationRequest commandCreationRequest) {
        if ( commandCreationRequest == null ) {
            return null;
        }

        CommandEntity.CommandEntityBuilder commandEntity = CommandEntity.builder();

        return commandEntity.build();
    }

    @Override
    public Command toCommand(CommandEntity commandEntity) {
        if ( commandEntity == null ) {
            return null;
        }

        Command command = new Command();

        command.setOrderedProducts( orderedProductEntitySetToOrderedProductCollection( commandEntity.getOrderedProductEntities() ) );

        return command;
    }

    @Override
    public OrderedProduct toOrderedProduct(OrderedProductEntity orderedProductEntity) {
        if ( orderedProductEntity == null ) {
            return null;
        }

        OrderedProduct orderedProduct = new OrderedProduct();

        orderedProduct.setQuantity( orderedProductEntity.getQuantity() );

        return orderedProduct;
    }

    @Override
    public OrderedProductResponse toResponse(OrderedProduct orderedProduct) {
        if ( orderedProduct == null ) {
            return null;
        }

        OrderedProductResponse orderedProductResponse = new OrderedProductResponse();

        orderedProductResponse.setQuantity( orderedProduct.getQuantity() );
        orderedProductResponse.setNameProduct( orderedProduct.getNameProduct() );

        return orderedProductResponse;
    }

    @Override
    public Set<CommandResponse> toResponses(Set<Command> commands) {
        if ( commands == null ) {
            return null;
        }

        Set<CommandResponse> set = new LinkedHashSet<CommandResponse>( Math.max( (int) ( commands.size() / .75f ) + 1, 16 ) );
        for ( Command command : commands ) {
            set.add( toResponse( command ) );
        }

        return set;
    }

    protected Collection<OrderedProductResponse> orderedProductCollectionToOrderedProductResponseCollection(Collection<OrderedProduct> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<OrderedProductResponse> collection1 = new ArrayList<OrderedProductResponse>( collection.size() );
        for ( OrderedProduct orderedProduct : collection ) {
            collection1.add( toResponse( orderedProduct ) );
        }

        return collection1;
    }

    protected Collection<OrderedProduct> orderedProductEntitySetToOrderedProductCollection(Set<OrderedProductEntity> set) {
        if ( set == null ) {
            return null;
        }

        Collection<OrderedProduct> collection = new ArrayList<OrderedProduct>( set.size() );
        for ( OrderedProductEntity orderedProductEntity : set ) {
            collection.add( toOrderedProduct( orderedProductEntity ) );
        }

        return collection;
    }
}
