package com.cms.controller;

import com.cms.entity.User;
import com.cms.repository.UserRepository;
import com.cms.service.ComplaintService;
import com.cms.entity.Complaint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepo;
    private final ComplaintService complaintService;

    public UserController(UserRepository userRepo, ComplaintService complaintService) {
        this.userRepo = userRepo;
        this.complaintService = complaintService;
    }

    @GetMapping("/register")
    public String registerPage(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        // NOTE: Password is stored plaintext for starter demo only. In production, hash passwords.
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/dashboard")
    public String dashboard(Principal principal, Model model) {
        model.addAttribute("user", principal == null ? "Guest" : principal.getName());
        return "user_dashboard";
    }

    @GetMapping("/user/my-complaints")
    public String myComplaints(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Complaint> complaints = complaintService.getByEmail(principal.getName());
        model.addAttribute("complaints", complaints);
        return "user_complaints";
    }
}
