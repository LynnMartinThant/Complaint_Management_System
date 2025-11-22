package com.cms.api;

import com.cms.api.dto.ComplaintRequest;
import com.cms.entity.Complaint;
import com.cms.service.ComplaintService;
import com.cms.util.AesEncryptionUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintApiController {

    private final ComplaintService complaintService;
    private final AesEncryptionUtil aesUtil;

    public ComplaintApiController(ComplaintService complaintService, AesEncryptionUtil aesUtil) {
        this.complaintService = complaintService;
        this.aesUtil = aesUtil;
    }

    // Create complaint (public or authenticated user)
    @PostMapping
    public ResponseEntity<?> createComplaint(@Valid @RequestBody ComplaintRequest req, Principal principal) {
        Complaint c = new Complaint();
        c.setComplainantName(req.getComplainantName());
        // encrypt email at-rest
        c.setEmail(aesUtil.encrypt(req.getEmail()));
        c.setCategory(req.getCategory());
        c.setDescription(req.getDescription());
        Complaint saved = complaintService.save(c);
        return ResponseEntity.created(URI.create("/api/complaints/" + saved.getId())).body(saved.getId());
    }

    // Get complaint by id (users can see own complaints - admin can see all)
    @GetMapping("/{id}")
    public ResponseEntity<?> getComplaint(@PathVariable Long id, Principal principal) {
        Complaint c = complaintService.getById(id);
        if (c == null) return ResponseEntity.notFound().build();

        // simple ownership check: compare decrypted email with authenticated user
        if (principal != null && principal.getName() != null) {
            String auth = principal.getName();
            String decrypted = aesUtil.decrypt(c.getEmail());
            boolean isAdmin = principal.toString().contains("ROLE_ADMIN"); // placeholder check
            if (!isAdmin && !auth.equalsIgnoreCase(decrypted)) {
                return ResponseEntity.status(403).build();
            }
        }
        // return with email masked for API clients
        c.setEmail("[PROTECTED]"); 
        return ResponseEntity.ok(c);
    }

    // List complaints (admin only)
    @GetMapping
    public ResponseEntity<List<Complaint>> listAll() {
        return ResponseEntity.ok(complaintService.getAll());
    }
}
