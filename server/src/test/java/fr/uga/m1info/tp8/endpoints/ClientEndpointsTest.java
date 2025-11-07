package fr.uga.m1info.tp8.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static fr.uga.m1info.tp8.utils.TestUtils.*;

class ClientEndpointsTest {

    Class<?> clientEndpointClassEndpointClass = Class.forName("fr.uga.m1info.tp8.endpoints.ClientEndpoints");

    public ClientEndpointsTest() throws ClassNotFoundException {}


    @Test
    void testAnnotationInClass() throws Exception {
        verifyClassHaveAnnotation(clientEndpointClassEndpointClass, RequestMapping.class);
        verifyClassHaveAnnotationField(clientEndpointClassEndpointClass, RequestMapping.class, "value", new String[]{"/api/client"});
        verifyClassHaveAnnotation(clientEndpointClassEndpointClass, Tag.class);
        verifyClassHaveAnnotationField(clientEndpointClassEndpointClass, Tag.class, "name", "Client");
        verifyClassHaveAnnotationField(clientEndpointClassEndpointClass, Tag.class, "description", "Création d'un client et récuperation de toutes ses commandes");
    }

    @Nested
    class Endpoint {
        @Test
        void createClient() throws Exception {
            verifyMethodIsDeclared(clientEndpointClassEndpointClass, "createClient");
            verifyMethodHaveAnnotation(clientEndpointClassEndpointClass, "createClient", Operation.class);
            verifyMethodAnnotationField(clientEndpointClassEndpointClass, "createClient", Operation.class, "description", "Créer un client");
            verifyApiResponse(clientEndpointClassEndpointClass, "createClient", 2);
            verifyApiResponseField(clientEndpointClassEndpointClass, "createClient", 0, "responseCode", "201");
            verifyApiResponseField(clientEndpointClassEndpointClass, "createClient", 0, "description", "Le client à bien été payé");
            verifyApiResponseField(clientEndpointClassEndpointClass, "createClient", 1, "responseCode", "400");
            verifyApiResponseField(clientEndpointClassEndpointClass, "createClient", 1, "description", "L'adresse email n'est pas au bon format");
            verifyMethodHaveAnnotation(clientEndpointClassEndpointClass, "createClient", ResponseStatus.class);
            verifyMethodAnnotationField(clientEndpointClassEndpointClass, "createClient", ResponseStatus.class, "value", HttpStatus.CREATED);
            verifyMethodHaveAnnotation(clientEndpointClassEndpointClass, "createClient", PostMapping.class);
            verifyMethodAnnotationField(clientEndpointClassEndpointClass, "createClient", PostMapping.class, "value", new String[]{});
            verifyMethodHaveParam(clientEndpointClassEndpointClass, "createClient", "request");
            verifyMethodParamHaveAnnotation(clientEndpointClassEndpointClass, "createClient", "request", RequestBody.class);
        }

        @Test
        void getAllCommand() throws Exception {
            verifyMethodIsDeclared(clientEndpointClassEndpointClass, "getAllCommand");
            verifyMethodHaveAnnotation(clientEndpointClassEndpointClass, "getAllCommand", Operation.class);
            verifyMethodAnnotationField(clientEndpointClassEndpointClass, "getAllCommand", Operation.class, "description", "Récupérer toutes les commandes d'un client");
            verifyApiResponse(clientEndpointClassEndpointClass, "getAllCommand", 2);
            verifyApiResponseField(clientEndpointClassEndpointClass, "getAllCommand", 0, "responseCode", "200");
            verifyApiResponseField(clientEndpointClassEndpointClass, "getAllCommand", 0, "description", "Toutes les commandes d'un client");
            verifyApiResponseField(clientEndpointClassEndpointClass, "getAllCommand", 1, "responseCode", "404");
            verifyApiResponseField(clientEndpointClassEndpointClass, "getAllCommand", 1, "description", "Le client n'existe pas");
            verifyMethodHaveAnnotation(clientEndpointClassEndpointClass, "getAllCommand", ResponseStatus.class);
            verifyMethodAnnotationField(clientEndpointClassEndpointClass, "getAllCommand", ResponseStatus.class, "value", HttpStatus.OK);
            verifyMethodHaveAnnotation(clientEndpointClassEndpointClass, "getAllCommand", GetMapping.class);
            verifyMethodAnnotationField(clientEndpointClassEndpointClass, "getAllCommand", GetMapping.class, "value", new String[]{"/{idClient}/commands"});
            verifyMethodHaveParam(clientEndpointClassEndpointClass, "getAllCommand", "idClient");
            verifyMethodParamHaveAnnotation(clientEndpointClassEndpointClass, "getAllCommand", "idClient", PathVariable.class);
            verifyMethodParamAnnotationField(clientEndpointClassEndpointClass, "getAllCommand", "idClient", PathVariable.class, "name", "idClient");
        }
    }

}
