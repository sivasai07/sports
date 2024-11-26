package com.auth.Authentication.Controller;

import com.auth.Authentication.entity.Coach;
import com.auth.Authentication.Services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    // Get all coaches
    @GetMapping("/all")
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    // Get coach by ID
    @GetMapping("/view/{id}")
    public Coach getCoachById(@PathVariable int id) {
        return coachService.getCoachById(id);
    }

    // Get coach by username
    @GetMapping("/view/username/{username}")
    public Coach getCoachByUsername(@PathVariable String username) {
        return coachService.getCoachByUsername(username);
    }

    // Create a new coach
    @PostMapping("/create")
    public Coach createCoach(@RequestBody Coach coach) {
        return coachService.saveCoach(coach);
    }

    // Update a coach
    @PutMapping("/update/{id}")
    public Coach updateCoach(@PathVariable int id, @RequestBody Coach coach) {
        coach.setCoachId(id);
        return coachService.saveCoach(coach);
    }

    // Delete a coach
    @DeleteMapping("/delete/{id}")
    public void deleteCoach(@PathVariable int id) {
        coachService.deleteCoach(id);
    }
}
