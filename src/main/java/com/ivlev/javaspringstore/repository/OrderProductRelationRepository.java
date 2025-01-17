package com.ivlev.javaspringstore.repository;

import com.ivlev.javaspringstore.entity.OrderProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderProductRelationRepository extends JpaRepository<OrderProductRelation, UUID> {

    @Query("FROM OrderProductRelation opr WHERE opr.order.id = :id")
    List<OrderProductRelation> findAllByOrderId(UUID id);

}