package com.cms.api;

import com.cms.api.dto.SolutionRequest;
import com.cms.entity.Complaint;
import com.cms.repository.ComplaintRepository;
import com.cms.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private final ComplaintRepository repo;
    private final EmailService emailService;

    public AdminApiController(ComplaintRepository repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }

    @PreAuthorize("hasRole('ADMIN')") // RBAC for admin functions
    @PostMapping("/resolve")
    public ResponseEntity<?> resolveComplaint(@Valid @RequestBody SolutionRequest req) {
        Complaint c = repo.findById(req.getId()).orElse(null);
        if (c == null) return ResponseEntity.notFound().build();
        c.setSolution(req.getSolution());
        c.setComplaintType(req.getComplaintType());
        c.setStatus("Resolved");
        c.setSolutionDate(LocalDateTime.now());
        repo.save(c);
        // send email notification (email stored encrypted; EmailService should decrypt or accept cipher)
        emailService.sendSolutionEmail(c);
        return ResponseEntity.ok().build();
    }
}
