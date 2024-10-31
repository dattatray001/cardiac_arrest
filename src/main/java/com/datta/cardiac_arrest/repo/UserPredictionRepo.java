package com.datta.cardiac_arrest.repo;


import com.datta.cardiac_arrest.model.UserPrediction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPredictionRepo extends MongoRepository<UserPrediction, String> {
}
