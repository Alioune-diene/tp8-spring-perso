package fr.uga.m1info.tp8.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static fr.uga.m1info.tp8.utils.TestUtils.*;

class CommandEndpointsTest {
    Class<?> commandEndpointClassEndpointClass = Class.forName("fr.uga.m1info.tp8.endpoints.CommandEndpoints");

    public CommandEndpointsTest() throws ClassNotFoundException {
    }


    @Test
    void testAnnotationInClass() throws Exception {
        verifyClassHaveAnnotation(commandEndpointClassEndpointClass, RequestMapping.class);
        verifyClassHaveAnnotationField(commandEndpointClassEndpointClass, RequestMapping.class, "value", new String[]{"/api/command"});
        verifyClassHaveAnnotation(commandEndpointClassEndpointClass, Tag.class);
        verifyClassHaveAnnotationField(commandEndpointClassEndpointClass, Tag.class, "name", "Gestion d'une commande");
        verifyClassHaveAnnotationField(commandEndpointClassEndpointClass, Tag.class, "description", "Gestion d'une commande");
    }

    @Nested
    class Endpoint {
        @Test
        void createCommand() throws Exception {
            verifyMethodIsDeclared(commandEndpointClassEndpointClass, "createCommand");
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "createCommand", Operation.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "createCommand", Operation.class, "description", "création d'une commande");
            verifyApiResponse(commandEndpointClassEndpointClass, "createCommand", 3);
            verifyApiResponseField(commandEndpointClassEndpointClass, "createCommand", 0, "responseCode", "201");
            verifyApiResponseField(commandEndpointClassEndpointClass, "createCommand", 0, "description", "La commande à bien été créer");
            verifyApiResponseField(commandEndpointClassEndpointClass, "createCommand", 1, "responseCode", "400");
            verifyApiResponseField(commandEndpointClassEndpointClass, "createCommand", 1, "description", "La commande n'a pas de produit");
            verifyApiResponseField(commandEndpointClassEndpointClass, "createCommand", 2, "responseCode", "404");
            verifyApiResponseField(commandEndpointClassEndpointClass, "createCommand", 2, "description", "Un des produits n'existe pas dans la commandes");
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "createCommand", ResponseStatus.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "createCommand", ResponseStatus.class, "value", HttpStatus.CREATED);
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "createCommand", PostMapping.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "createCommand", PostMapping.class, "value", new String[]{});
            verifyMethodHaveParam(commandEndpointClassEndpointClass, "createCommand", "request");
            verifyMethodParamHaveAnnotation(commandEndpointClassEndpointClass, "createCommand", "request", RequestBody.class);
        }

        @Test
        void addProducts() throws Exception {
            verifyMethodIsDeclared(commandEndpointClassEndpointClass, "addProducts");
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "addProducts", Operation.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "addProducts", Operation.class, "description", "Ajout d'un produit dans une commande existante");
            verifyApiResponse(commandEndpointClassEndpointClass, "addProducts", 3);
            verifyApiResponseField(commandEndpointClassEndpointClass, "addProducts", 0, "responseCode", "200");
            verifyApiResponseField(commandEndpointClassEndpointClass, "addProducts", 0, "description", "le produit est ajouté à la commande");
            verifyApiResponseField(commandEndpointClassEndpointClass, "addProducts", 1, "responseCode", "404");
            verifyApiResponseField(commandEndpointClassEndpointClass, "addProducts", 1, "description", "le produit à ajouter n'existe à la commande n'existe pas");
            verifyApiResponseField(commandEndpointClassEndpointClass, "addProducts", 2, "responseCode", "404");
            verifyApiResponseField(commandEndpointClassEndpointClass, "addProducts", 2, "description", "la commande n'existe pas");
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "addProducts", ResponseStatus.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "addProducts", ResponseStatus.class, "value", HttpStatus.OK);
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "addProducts", PutMapping.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "addProducts", PutMapping.class, "value", new String[]{"/{idCommand}/products/add"});
            verifyMethodHaveParam(commandEndpointClassEndpointClass, "addProducts", "idCommand");
            verifyMethodParamHaveAnnotation(commandEndpointClassEndpointClass, "addProducts", "idCommand", PathVariable.class);
            verifyMethodParamAnnotationField(commandEndpointClassEndpointClass, "addProducts", "idCommand", PathVariable.class, "name", "idCommand");
            verifyMethodHaveParam(commandEndpointClassEndpointClass, "addProducts", "request");
            verifyMethodParamHaveAnnotation(commandEndpointClassEndpointClass, "addProducts", "request", RequestBody.class);
        }

        @Test
        void deleteProducts() throws Exception {
            verifyMethodIsDeclared(commandEndpointClassEndpointClass, "deleteProducts");
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "deleteProducts", Operation.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "deleteProducts", Operation.class, "description", "Suppression d'un produit dans une commande existante");
            verifyApiResponse(commandEndpointClassEndpointClass, "deleteProducts", 4);
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 0, "responseCode", "200");
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 0, "description", "les produits ont été supprimé de la commande");
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 1, "responseCode", "400");
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 1, "description", "Un des produits n'est pas dans la commande");
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 2, "responseCode", "400");
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 2, "description", "la commande n'a plus qu'un produit");
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 3, "responseCode", "404");
            verifyApiResponseField(commandEndpointClassEndpointClass, "deleteProducts", 3, "description", "la commande n'a plus qu'un produit");
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "deleteProducts", ResponseStatus.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "deleteProducts", ResponseStatus.class, "value", HttpStatus.OK);
            verifyMethodHaveAnnotation(commandEndpointClassEndpointClass, "deleteProducts", PutMapping.class);
            verifyMethodAnnotationField(commandEndpointClassEndpointClass, "deleteProducts", PutMapping.class, "value", new String[]{"/{idCommand}/products/remove"});
            verifyMethodHaveParam(commandEndpointClassEndpointClass, "deleteProducts", "idCommand");
            verifyMethodParamHaveAnnotation(commandEndpointClassEndpointClass, "deleteProducts", "idCommand", PathVariable.class);
            verifyMethodParamAnnotationField(commandEndpointClassEndpointClass, "deleteProducts", "idCommand", PathVariable.class, "name", "idCommand");
            verifyMethodHaveParam(commandEndpointClassEndpointClass, "deleteProducts", "request");
            verifyMethodParamHaveAnnotation(commandEndpointClassEndpointClass, "deleteProducts", "request", RequestBody.class);
        }
    }


}
