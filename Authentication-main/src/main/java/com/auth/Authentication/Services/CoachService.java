package com.auth.Authentication.Services;
import com.auth.Authentication.entity.Coach;
import com.auth.Authentication.Repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    // Get all coaches
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    // Get coach by ID
    public Coach getCoachById(int coachId) {
        return coachRepository.findById(coachId).orElse(null);
    }

    // Get coach by username
    public Coach getCoachByUsername(String username) {
        return coachRepository.findByUsername(username);
    }

    // Save or update a coach
    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    // Delete a coach
    public void deleteCoach(int coachId) {
        coachRepository.deleteById(coachId);
    }
}
