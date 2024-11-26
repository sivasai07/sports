package com.auth.Authentication.Services;

import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.entity.Coach;
import com.auth.Authentication.Repository.AthleteRepository;
import com.auth.Authentication.Repository.CoachRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final CoachRepository coachRepository;

    // Constructor to inject dependencies
    public AthleteService(AthleteRepository athleteRepository, CoachRepository coachRepository) {
        this.athleteRepository = athleteRepository;
        this.coachRepository = coachRepository;
    }

    // Get all athletes
    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    // Get athlete by ID
    public ResponseEntity<Athlete> getAthleteById(Integer id) {
        Optional<Athlete> athlete = athleteRepository.findById(id);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get athlete by username
    public ResponseEntity<Athlete> getAthleteByUsername(String username) {
        Optional<Athlete> athlete = athleteRepository.findByUsername(username);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new athlete (with or without a coach)
    public Athlete createAthlete(Athlete athlete) {
        if (athlete.getCoach() != null && athlete.getCoach().getCoachId() != 0) {
            // Fetch the Coach entity by coachId
            Coach coach = coachRepository.findById(athlete.getCoach().getCoachId())
                    .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
            athlete.setCoach(coach); // Set the coach entity
        }

        // Save the athlete entity
        return athleteRepository.save(athlete);
    }

    // Update the coach of an athlete (admin only)
    public ResponseEntity<Athlete> updateCoach(Integer id, Integer coachId) {
        Optional<Athlete> athleteOptional = athleteRepository.findById(id);
        if (athleteOptional.isPresent()) {
            Athlete athlete = athleteOptional.get();

            // Fetch the coach by ID and set it to the athlete
            Coach coach = coachRepository.findById(coachId)
                    .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
            athlete.setCoach(coach); // Update only the coach

            return ResponseEntity.ok(athleteRepository.save(athlete)); // Save the updated athlete
        }
        return ResponseEntity.notFound().build();
    }

    // Update athlete details, but do not update coach (coach update is separate)
    public ResponseEntity<Athlete> updateAthlete(Integer id, Athlete athleteDetails) {
        Optional<Athlete> athleteOptional = athleteRepository.findById(id);
        if (athleteOptional.isPresent()) {
            Athlete athlete = athleteOptional.get();

            // Update other details of the athlete, but not the coach
            athlete.setUserld(athleteDetails.getUserld());
            athlete.setFirstName(athleteDetails.getFirstName());
            athlete.setLastName(athleteDetails.getLastName());
            athlete.setBirthDate(athleteDetails.getBirthDate());
            athlete.setGender(athleteDetails.getGender());
            athlete.setHeight(athleteDetails.getHeight());
            athlete.setWeight(athleteDetails.getWeight());
            athlete.setCategory(athleteDetails.getCategory());
            athlete.setPhotoUrl(athleteDetails.getPhotoUrl());
            athlete.setUsername(athleteDetails.getUsername());

            return ResponseEntity.ok(athleteRepository.save(athlete));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete an athlete by ID
    public ResponseEntity<Void> deleteAthlete(Integer id) {
        if (athleteRepository.existsById(id)) {
            athleteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
