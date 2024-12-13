package com.auth.Authentication.Controller;

import com.auth.Authentication.Services.EventService;
import com.auth.Authentication.entity.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Create a new event
    @PostMapping("/create")
    public ResponseEntity<Event> createNewEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    // Update an existing event
    @PutMapping("/update/{eventId}")
    public ResponseEntity<Event> modifyEventDetails(@PathVariable Integer eventId, @RequestBody Event updatedEvent) {
        Event event = eventService.updateEvent(eventId, updatedEvent);
        return ResponseEntity.ok(event);
    }

    // Delete an event
    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Void> removeEvent(@PathVariable Integer eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }

    // Get all events
    @GetMapping("/list")
    public ResponseEntity<List<Event>> listAllEvents() {
        List<Event> events = eventService.findAllEvents();
        return ResponseEntity.ok(events);
    }
    // Get events registered by an athlete
    @GetMapping("/athlete/{athleteId}/registered")
    public ResponseEntity<List<Event>> getEventsRegisteredByAthlete(@PathVariable Integer athleteId) {
        List<Event> registeredEvents = eventService.findEventsRegisteredByAthlete(athleteId);
        return ResponseEntity.ok(registeredEvents);
    }

    // Get events approved for an athlete
    @GetMapping("/athlete/{athleteId}/approved")
    public ResponseEntity<List<Event>> getEventsApprovedForAthlete(@PathVariable Integer athleteId) {
        List<Event> approvedEvents = eventService.findEventsApprovedForAthlete(athleteId);
        return ResponseEntity.ok(approvedEvents);
    }
    // Get a specific event by ID
    @GetMapping("/details/{eventId}")
    public ResponseEntity<Event> fetchEventById(@PathVariable Integer eventId) {
        Event event = eventService.findEventById(eventId);
        return ResponseEntity.ok(event);
    }

    // Register an athlete to an event
    @PostMapping("/{eventId}/athlete/{athleteId}/register")
    public ResponseEntity<Event> enrollAthlete(@PathVariable Integer eventId, @PathVariable Integer athleteId) {
        Event event = eventService.registerAthlete(eventId, athleteId);
        return ResponseEntity.ok(event);
    }

    // Accept an athlete's registration
    @PostMapping("/{eventId}/athlete/{athleteId}/accept")
    public ResponseEntity<Event> approveAthlete(@PathVariable Integer eventId, @PathVariable Integer athleteId) {
        Event event = eventService.acceptAthlete(eventId, athleteId);
        return ResponseEntity.ok(event);
    }

    // Decline an athlete's registration
    @PostMapping("/{eventId}/athlete/{athleteId}/decline")
    public ResponseEntity<Event> rejectAthlete(@PathVariable Integer eventId, @PathVariable Integer athleteId) {
        Event event = eventService.declineAthlete(eventId, athleteId);
        return ResponseEntity.ok(event);
    }

    // Get the registration status of an athlete for a specific event
    @GetMapping("/{eventId}/athlete/{athleteId}/status")
    public ResponseEntity<String> checkAthleteStatus(@PathVariable Integer eventId, @PathVariable Integer athleteId) {
        String status = "Not Registered";
        Event event = eventService.findEventById(eventId);

        if (event.getAcceptedAthletes().contains(athleteId)) {
            status = "Accepted";
        } else if (event.getDeclinedAthletes().contains(athleteId)) {
            status = "Declined";
        } else if (event.getRegisteredAthletes().contains(athleteId)) {
            status = "Registered";
        }

        return ResponseEntity.ok(status);
    }
}
