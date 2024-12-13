package com.auth.Authentication.Controller;

import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.Services.AthleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    // Get all athletes
    @GetMapping
    public List<Athlete> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    // Get a single athlete by ID
    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Integer id) {
        return athleteService.getAthleteById(id);
    }
    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<Athlete> getAthleteByUserId(@PathVariable Integer userId) {
        return athleteService.getAthleteByUserId(userId);
    }
    @GetMapping("/getIdByUserId/{userId}")
    public ResponseEntity<?> getAthleteIdByUserId(@PathVariable Integer userId) {
        try {
            Integer athleteId = athleteService.getAthleteIdByUserId(userId);

            if (athleteId != null) {
                return ResponseEntity.ok(athleteId); // Sends the athleteId as plain JSON
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Athlete not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // Search for an athlete by username
    @GetMapping("/searchByUsername")
    public ResponseEntity<Athlete> getAthleteByUsername(@RequestParam String username) {
        return athleteService.getAthleteByUsername(username);
    }

    // Create a new athlete (with or without a coach)
    @PostMapping("/create")
    public Athlete createAthlete(@RequestBody Athlete athlete) {
        return athleteService.createAthlete(athlete);
    }

    // Update an existing athlete (but not the coach)
    @PutMapping("/update/{id}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable Integer id, @RequestBody Athlete athleteDetails) {
        return athleteService.updateAthlete(id, athleteDetails);
    }
    @PutMapping("/updateByUserId/{userId}")
    public ResponseEntity<Athlete> updateAthleteByUserId(@PathVariable Integer userId, @RequestBody Athlete athleteDetails) {
        return athleteService.updateAthleteByUserId(userId, athleteDetails);
    }
    // Update only the coach of an athlete
    @PutMapping("/updateCoach/{id}")
    public ResponseEntity<Athlete> updateCoach(@PathVariable Integer id, @RequestParam Integer coachId) {
        return athleteService.updateCoach(id, coachId);
    }

    // Delete an athlete by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Integer id) {
        return athleteService.deleteAthlete(id);
    }
}
