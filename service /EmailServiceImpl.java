package com.cms.service;

import com.cms.entity.Complaint;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSolutionEmail(Complaint complaint) {
        // Simple implementation; configure mail properties in application.properties
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(complaint.getEmail());
            mail.setSubject("Complaint Resolution: " + complaint.getId());
            mail.setText("Hello " + complaint.getComplainantName() + ",\n\nYour complaint has been resolved.\nSolution: " + complaint.getSolution());
            mailSender.send(mail);
        } catch (Exception ex) {
            // for starter project, just print
            System.out.println("Email send failed: " + ex.getMessage());
        }
    }
}
