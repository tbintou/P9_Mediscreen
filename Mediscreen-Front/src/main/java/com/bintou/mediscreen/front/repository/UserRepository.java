package com.bintou.mediscreen.front.repository;

import com.bintou.mediscreen.front.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}
