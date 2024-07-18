package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.entity.OrderProductRelation;
import com.ivlev.javaspringstore.repository.OrderProductRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProductRelationService {

    private final OrderProductRelationRepository orderProductRelationRepository;

    public void addRelations(List<OrderProductRelation> orderProductRelations) {

        orderProductRelationRepository.saveAll(orderProductRelations);

    }

    public List<OrderProductRelation> findAllByOrderId(UUID id) {

        return orderProductRelationRepository.findAllByOrderId(id);

    }
}
