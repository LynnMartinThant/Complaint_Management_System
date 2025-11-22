package com.cms.service;

import com.cms.entity.Complaint;
import java.util.List;

public interface ComplaintService {
    Complaint save(Complaint complaint);
    List<Complaint> getAll();
    Complaint getById(Long id);
    List<Complaint> getByEmail(String email);
}
