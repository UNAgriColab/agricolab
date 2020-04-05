package com.example.agricolab.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users , Long> {
    List<Users> findByEmail(String mail);
}
