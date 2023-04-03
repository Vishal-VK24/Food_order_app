package com.example.Identity.service.Repository;

import com.example.Identity.service.Model.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserCredentialRepository extends MongoRepository<UserCredential,Integer> {

    @Query("{'email':?0}")
    UserCredential findUserByEmail(String email);
}
