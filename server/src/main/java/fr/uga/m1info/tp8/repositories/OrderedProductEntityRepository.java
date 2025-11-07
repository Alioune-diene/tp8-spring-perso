package fr.uga.m1info.tp8.repositories;

import fr.uga.m1info.tp8.models.OrderedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductEntityRepository extends JpaRepository<OrderedProductEntity, Long> {
}