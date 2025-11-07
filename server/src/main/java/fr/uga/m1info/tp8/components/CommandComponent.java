package fr.uga.m1info.tp8.components;

import fr.uga.m1info.tp8.domain.models.Command;
import fr.uga.m1info.tp8.exceptions.technical.CommandEntityNotFoundException;
import fr.uga.m1info.tp8.mappers.CommandMapper;
import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.models.OrderedProductEntity;
import fr.uga.m1info.tp8.repositories.CommandEntityRepository;
import fr.uga.m1info.tp8.repositories.ProductEntityRepository;
import fr.uga.m1info.tp8.requests.CommandAddProductRequest;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;
import fr.uga.m1info.tp8.requests.CommandRemoveProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandComponent {
    private final CommandEntityRepository commandEntityRepository;
    private final CommandMapper commandMapper;
    private final ProductEntityRepository productEntityRepository;
/* TO DO
    public Command createCommand(CommandCreationRequest request) {
        return null;
    }
 */
/* TO DO
    public Command addProducts(Long idCommand, CommandAddProductRequest request) throws CommandEntityNotFoundException {
        return null;
    }
*/
/* TO DO
    public Command getCommandById(Long idCommand) throws CommandEntityNotFoundException {
        return null;
    }
*/
/* TO DO
    public Command deleteProducts(Long idCommand, CommandRemoveProductsRequest request) throws CommandEntityNotFoundException {
        return null;
    }
*/
}
