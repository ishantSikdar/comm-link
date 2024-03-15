package com.ctrlcreate.commlink.repository.user;

import com.ctrlcreate.commlink.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'email' : ?0 }")
    Optional<User> findByEmail(String email);

    @Query("{ 'type' : ?0 }")
    List<User> findAllOrganisations(String type);

}
