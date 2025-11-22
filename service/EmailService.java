package com.cms.service;

import com.cms.entity.Complaint;

public interface EmailService {
    void sendSolutionEmail(Complaint complaint);
}
