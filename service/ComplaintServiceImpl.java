package com.cms.service;

import com.cms.entity.Complaint;
import com.cms.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;

    public ComplaintServiceImpl(ComplaintRepository repo) {
        this.repo = repo;
    }

    @Override
    public Complaint save(Complaint complaint) {
        return repo.save(complaint);
    }

    @Override
    public List<Complaint> getAll() {
        return repo.findAll();
    }

    @Override
    public Complaint getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Complaint> getByEmail(String email) {
        return repo.findByEmail(email);
    }
}
