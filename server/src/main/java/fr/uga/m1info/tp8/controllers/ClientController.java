package fr.uga.m1info.tp8.controllers;

import fr.uga.m1info.tp8.endpoints.ClientEndpoints;
import fr.uga.m1info.tp8.mappers.ClientMapper;
import fr.uga.m1info.tp8.mappers.CommandMapper;
import fr.uga.m1info.tp8.requests.ClientCreationRequest;
import fr.uga.m1info.tp8.responses.ClientResponse;
import fr.uga.m1info.tp8.responses.CommandResponse;
import fr.uga.m1info.tp8.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
public class ClientController implements ClientEndpoints {
    private final ClientService clientService;
    private final CommandMapper commandMapper;
    private final ClientMapper clientMapper;

    @Override
    public Set<CommandResponse> getAllCommand(Long idClient) {
        return commandMapper.toResponses(clientService.getAllCommandByClient(idClient));
    }

    @Override
    public ClientResponse createClient(ClientCreationRequest request) {
        return clientMapper.toResponse(clientService.createClient(request));
    }
}
