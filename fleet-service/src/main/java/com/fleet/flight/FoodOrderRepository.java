package com.fleet.flight;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodOrderRepository extends MongoRepository<FoodOrder, String> {
}
