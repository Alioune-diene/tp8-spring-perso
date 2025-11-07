package fr.uga.m1info.tp8.repositories;

import fr.uga.m1info.tp8.models.CommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandEntityRepository extends JpaRepository<CommandEntity, Long> {
}