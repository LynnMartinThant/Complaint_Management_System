package com.cms.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SolutionRequest {

    private Long id;

    @NotBlank @Size(min = 5, max = 2000)
    private String solution;

    private String complaintType;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }
    public String getComplaintType() { return complaintType; }
    public void setComplaintType(String complaintType) { this.complaintType = complaintType; }
}
