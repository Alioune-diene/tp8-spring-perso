package fr.uga.m1info.tp8.components;

import fr.uga.m1info.tp8.domain.models.Client;
import fr.uga.m1info.tp8.exceptions.technical.ClientEntityNotFoundException;
import fr.uga.m1info.tp8.mappers.ClientMapper;
import fr.uga.m1info.tp8.repositories.ClientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientComponent {
    private final ClientEntityRepository clientEntityRepository;
    private final ClientMapper clientMapper;

    public Client getClient(Long idClient) throws ClientEntityNotFoundException {
        return clientMapper.toClient(clientEntityRepository.findById(idClient)
                .orElseThrow(() -> new ClientEntityNotFoundException(String.format("Le client [%s] n'existe pas", idClient))));
    }
}
