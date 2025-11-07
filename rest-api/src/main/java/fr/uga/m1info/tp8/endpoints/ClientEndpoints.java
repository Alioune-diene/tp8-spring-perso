package fr.uga.m1info.tp8.endpoints;

import fr.uga.m1info.tp8.requests.ClientCreationRequest;
import fr.uga.m1info.tp8.responses.ClientResponse;
import fr.uga.m1info.tp8.responses.CommandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@Tag(name = "Client", description = "Création d'un client et récuperation de toutes ses commandes")
@RequestMapping("/api/client")
public interface ClientEndpoints {

    @Operation(description = "Créer un client")
    @ApiResponse(responseCode = "201", description = "Le client à bien été payé")
    @ApiResponse(responseCode = "400", description = "L'adresse email n'est pas au bon format")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ClientResponse createClient(@RequestBody ClientCreationRequest request);
    
    @Operation(description = "Récupérer toutes les commandes d'un client")
    @ApiResponse(responseCode = "200", description = "Toutes les commandes d'un client")
    @ApiResponse(responseCode = "404", description = "Le client n'existe pas")
    @GetMapping("/{idClient}/commands")
    @ResponseStatus(HttpStatus.OK)
    Set<CommandResponse> getAllCommand(@PathVariable(name = "idClient") Long idClient);
}
