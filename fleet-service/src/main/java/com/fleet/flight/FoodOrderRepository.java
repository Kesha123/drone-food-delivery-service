package com.fleet.flight;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FoodOrderRepository extends MongoRepository<FoodOrder, String> {
    Optional<List<FoodOrder>> findByStatus(OrderStatus status, Sort sort);
}
