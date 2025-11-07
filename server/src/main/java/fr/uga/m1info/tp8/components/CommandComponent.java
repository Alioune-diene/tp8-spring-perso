package fr.uga.m1info.tp8.components;

import fr.uga.m1info.tp8.domain.models.Command;
import fr.uga.m1info.tp8.exceptions.technical.CommandEntityNotFoundException;
import fr.uga.m1info.tp8.mappers.CommandMapper;
import fr.uga.m1info.tp8.models.CommandEntity;
import fr.uga.m1info.tp8.repositories.CommandEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandComponent {
    private final CommandEntityRepository commandEntityRepository;
    private final CommandMapper commandMapper;

    public Command getCommandById(Long idCommand) throws CommandEntityNotFoundException {
        return commandMapper.toCommand(getCommandEntityById(idCommand));
    }

    public CommandEntity getCommandEntityById(Long idCommand) throws CommandEntityNotFoundException {
        return commandEntityRepository.findById(idCommand)
                .orElseThrow(() -> new CommandEntityNotFoundException(
                        String.format("La commande [%s] n'existe pas", idCommand)));
    }
}
