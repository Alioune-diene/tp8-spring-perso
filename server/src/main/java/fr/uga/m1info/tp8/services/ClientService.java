package fr.uga.m1info.tp8.services;

import fr.uga.m1info.tp8.components.ClientComponent;
import fr.uga.m1info.tp8.domain.models.Client;
import fr.uga.m1info.tp8.domain.models.Command;
// import fr.uga.m1info.tp8.exceptions.rest.BadRequestRestException;
import fr.uga.m1info.tp8.exceptions.rest.ClientNotFoundRestException;
import fr.uga.m1info.tp8.exceptions.rest.EmailInvalidFormatRestException;
import fr.uga.m1info.tp8.exceptions.technical.ClientEntityNotFoundException;
import fr.uga.m1info.tp8.mappers.ClientMapper;
import fr.uga.m1info.tp8.models.ClientEntity;
import fr.uga.m1info.tp8.repositories.ClientEntityRepository;
import fr.uga.m1info.tp8.requests.ClientCreationRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientEntityRepository clientEntityRepository;
    private final ClientMapper clientMapper;
    private final ClientComponent clientComponent;

    public Client createClient(@RequestBody ClientCreationRequest request){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (request.email() == null || !pattern.matcher(request.email()).matches()) {
            throw new EmailInvalidFormatRestException("Email invalide : " + request.email());
        }
        ClientEntity entity = clientMapper.toEntity(request);
        ClientEntity saved = clientEntityRepository.save(entity);
        return clientMapper.toClient(saved);
    }

    public Set<Command> getAllCommandByClient(Long idClient) {
        try {
            return clientComponent.getClient(idClient).getCommands();
        } catch (ClientEntityNotFoundException e) {
            throw new ClientNotFoundRestException(
                    e.getMessage());
        }
    }
}
