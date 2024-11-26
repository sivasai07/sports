package com.auth.Authentication.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer athleteld;  // Athlete ID

    @Column(nullable = false)
    private Integer userld; // User ID

    @Column(nullable = false, length = 50)
    private String username; // Username

    @Column(nullable = false, length = 50)
    private String firstName; // First Name

    @Column(nullable = false, length = 50)
    private String lastName; // Last Name

    @Column(nullable = false)
    private LocalDate birthDate; // Date of Birth

    @Column(nullable = false, length = 10)
    private String gender; // Gender

    @Column(nullable = false)
    private Float height; // Height

    @Column(nullable = false)
    private Float weight; // Weight

    @Column(nullable = false, length = 50)
    private String category; // Category (e.g., "Sprinter")

    private String photoUrl; // URL for athlete's photo

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Created timestamp

    private LocalDateTime updatedAt = LocalDateTime.now(); // Updated timestamp

    @ManyToOne
    @JoinColumn(name = "coachId", referencedColumnName = "coachId", nullable = true) // Allow nullable coachId
    private Coach coach; // Coach entity reference
    public int getCoachId() {
        if (coach != null) {
            return  coach.getCoachId();
        }
        return 0;
    }
    // Getters and Setters
    public Integer getAthleteld() {
        return athleteld;
    }

    public void setAthleteld(Integer athleteld) {
        this.athleteld = athleteld;
    }

    public Integer getUserld() {
        return userld;
    }

    public void setUserld(Integer userld) {
        this.userld = userld;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
