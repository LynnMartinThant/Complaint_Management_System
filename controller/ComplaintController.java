package com.cms.controller;

import com.cms.entity.Complaint;
import com.cms.service.ComplaintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @GetMapping("/complaint-form")
    public String showForm(Complaint complaint) {
        return "complaint_form";
    }

    @PostMapping("/submit-complaint")
    public String submitComplaint(Complaint complaint) {
        service.save(complaint);
        return "success";
    }

    @GetMapping("/user/complaint-form")
    public String showUserForm(Complaint complaint) {
        return "complaint_form";
    }
}
