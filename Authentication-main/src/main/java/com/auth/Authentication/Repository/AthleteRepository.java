package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
    Optional<Athlete> findByUsername(String username);
}
