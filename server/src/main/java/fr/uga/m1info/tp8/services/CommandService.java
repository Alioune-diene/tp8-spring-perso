package fr.uga.m1info.tp8.services;

import fr.uga.m1info.tp8.domain.models.Command;
import fr.uga.m1info.tp8.requests.CommandAddProductRequest;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;
import fr.uga.m1info.tp8.requests.CommandRemoveProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandService {

    // TODO 🚧 
    public Command createCommand(CommandCreationRequest request) {
        return null;
    }

    // TODO 🚧 
    public Command addProducts(Long idCommand, CommandAddProductRequest request) {
        return null;
    }

    // TODO 🚧 
    public Command removeProducts(Long idCommand, CommandRemoveProductsRequest request) {
        return null;
    }
}
