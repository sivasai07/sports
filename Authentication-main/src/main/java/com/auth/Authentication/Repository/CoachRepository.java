package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
    Coach findByUsername(String username); // Custom method to find by username
}
