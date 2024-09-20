package com.datta.cardiac_arrest.repo;


import com.datta.cardiac_arrest.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends MongoRepository<User, String> {

	User findByUsername(String username);
}
