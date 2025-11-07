package fr.uga.m1info.tp8.controllers;

import fr.uga.m1info.tp8.endpoints.CommandEndpoints;
import fr.uga.m1info.tp8.mappers.CommandMapper;
import fr.uga.m1info.tp8.requests.CommandAddProductRequest;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;
import fr.uga.m1info.tp8.requests.CommandRemoveProductsRequest;
import fr.uga.m1info.tp8.responses.CommandResponse;
import fr.uga.m1info.tp8.services.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommandController implements CommandEndpoints {
    private final CommandService commandService;
    private final CommandMapper commandMapper;
    // TO DO
}
