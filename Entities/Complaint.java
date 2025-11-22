package com.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String complainantName;
    private String email;
    private String category;
    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String solution;
    private String status = "Pending";
    private String complaintType;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime solutionDate;

    public Complaint() {}

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getComplainantName() { return complainantName; }
    public void setComplainantName(String complainantName) { this.complainantName = complainantName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getComplaintType() { return complaintType; }
    public void setComplaintType(String complaintType) { this.complaintType = complaintType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getSolutionDate() { return solutionDate; }
    public void setSolutionDate(LocalDateTime solutionDate) { this.solutionDate = solutionDate; }
}
