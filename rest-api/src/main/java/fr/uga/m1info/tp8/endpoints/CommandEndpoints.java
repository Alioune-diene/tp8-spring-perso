package fr.uga.m1info.tp8.endpoints;

import fr.uga.m1info.tp8.requests.CommandAddProductRequest;
import fr.uga.m1info.tp8.requests.CommandCreationRequest;
import fr.uga.m1info.tp8.requests.CommandRemoveProductsRequest;
import fr.uga.m1info.tp8.responses.CommandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gestion d'une commande", description = "Gestion d'une commande")
@RequestMapping("/api/command")
public interface CommandEndpoints {
/*
    @Operation(description = "création d'une commande")
    @ApiResponse(responseCode = "201", description = "La commande à bien été créer")
    @ApiResponse(responseCode = "400", description = "La commande n'a pas de produit")
    @ApiResponse(responseCode = "404", description = "Un des produits n'existe pas dans la commandes")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CommandResponse createCommand(@RequestBody CommandCreationRequest request);
*/
/*
    @Operation(description = "Ajout d'un produit dans une commande existante")
    @ApiResponse(responseCode = "200", description = "le produit est ajouté à la commande")
    @ApiResponse(responseCode = "404", description = "le produit à ajouter n'existe à la commande n'existe pas")
    @ApiResponse(responseCode = "404", description = "la commande n'existe pas")
    @PutMapping("/{idCommand}/products/add")
    @ResponseStatus(HttpStatus.OK)
    CommandResponse addProducts(@PathVariable(name = "idCommand") Long idCommand, @RequestBody CommandAddProductRequest request);
*/
/*
    @Operation(description = "Suppression d'un produit dans une commande existante")
    @ApiResponse(responseCode = "200", description = "les produits ont été supprimé de la commande")
    @ApiResponse(responseCode = "400", description = "Un des produits n'est pas dans la commande")
    @ApiResponse(responseCode = "400", description = "la commande n'a plus qu'un produit")
    @ApiResponse(responseCode = "404", description = "la commande n'a plus qu'un produit")
    @PutMapping("/{idCommand}/products/remove")
    @ResponseStatus(HttpStatus.OK)
    CommandResponse deleteProducts(@PathVariable(name = "idCommand") Long idCommand, @RequestBody CommandRemoveProductsRequest request);
 */
}
