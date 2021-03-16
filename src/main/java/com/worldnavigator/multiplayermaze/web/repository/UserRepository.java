package com.worldnavigator.multiplayermaze.web.repository;

import com.worldnavigator.multiplayermaze.web.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
}
