package fr.uga.m1info.tp8.mappers;

import fr.uga.m1info.tp8.domain.models.Client;
import fr.uga.m1info.tp8.domain.models.OrderedProduct;
import fr.uga.m1info.tp8.models.ClientEntity;
import fr.uga.m1info.tp8.requests.ClientCreationRequest;
import fr.uga.m1info.tp8.responses.ClientResponse;
import fr.uga.m1info.tp8.responses.OrderedProductResponse;
import org.mapstruct.Mapper;



@Mapper(uses = CommandMapper.class)
public interface ClientMapper {

     Client toClient(ClientEntity clientEntity);
    
    ClientResponse toResponse(Client client);

    ClientEntity toEntity(ClientCreationRequest request);

    OrderedProductResponse toOrderedProductResponse(OrderedProduct orderedProduct);
}
