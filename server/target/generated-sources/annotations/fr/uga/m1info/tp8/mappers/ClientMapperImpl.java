package fr.uga.m1info.tp8.mappers;

import fr.uga.m1info.tp8.domain.models.Client;
import fr.uga.m1info.tp8.domain.models.Command;
import fr.uga.m1info.tp8.domain.models.OrderedProduct;
import fr.uga.m1info.tp8.models.ClientEntity;
import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.requests.ClientCreationRequest;
import fr.uga.m1info.tp8.responses.ClientResponse;
import fr.uga.m1info.tp8.responses.OrderedProductResponse;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-07T22:51:52+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.9 (Debian)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Autowired
    private CommandMapper commandMapper;

    @Override
    public Client toClient(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return null;
        }

        Client client = new Client();

        client.setName( clientEntity.getName() );
        client.setEmail( clientEntity.getEmail() );
        client.setCommands( commandEntitySetToCommandSet( clientEntity.getCommands() ) );

        return client;
    }

    @Override
    public ClientResponse toResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setName( client.getName() );
        clientResponse.setEmail( client.getEmail() );
        clientResponse.setCommands( commandMapper.toResponses( client.getCommands() ) );

        return clientResponse;
    }

    @Override
    public ClientEntity toEntity(ClientCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        ClientEntity.ClientEntityBuilder clientEntity = ClientEntity.builder();

        clientEntity.name( request.name() );
        clientEntity.email( request.email() );

        return clientEntity.build();
    }

    @Override
    public OrderedProductResponse toOrderedProductResponse(OrderedProduct orderedProduct) {
        if ( orderedProduct == null ) {
            return null;
        }

        OrderedProductResponse orderedProductResponse = new OrderedProductResponse();

        orderedProductResponse.setQuantity( orderedProduct.getQuantity() );
        orderedProductResponse.setNameProduct( orderedProduct.getNameProduct() );

        return orderedProductResponse;
    }

    protected Set<Command> commandEntitySetToCommandSet(Set<CommandEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Command> set1 = new LinkedHashSet<Command>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CommandEntity commandEntity : set ) {
            set1.add( commandMapper.toCommand( commandEntity ) );
        }

        return set1;
    }
}
