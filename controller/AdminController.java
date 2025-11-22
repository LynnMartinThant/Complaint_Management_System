package com.cms.controller;

import com.cms.entity.Complaint;
import com.cms.repository.ComplaintRepository;
import com.cms.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ComplaintRepository repo;
    private final EmailService emailService;

    public AdminController(ComplaintRepository repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }

    @GetMapping("/complaints")
    public String listComplaints(Model model) {
        model.addAttribute("complaints", repo.findAll());
        return "admin_complaints";
    }

    @GetMapping("/resolve/{id}")
    public String openResolutionForm(@PathVariable Long id, Model model) {
        model.addAttribute("complaint", repo.findById(id).orElse(null));
        return "admin_resolve";
    }

    @PostMapping("/resolve")
    public String submitResolution(Complaint complaint) {
        complaint.setSolutionDate(LocalDateTime.now());
        complaint.setStatus("Resolved");
        repo.save(complaint);
        emailService.sendSolutionEmail(complaint);
        return "redirect:/admin/complaints";
    }

    @GetMapping("/login")
    public String login() {
        return "admin_login";
    }
}
