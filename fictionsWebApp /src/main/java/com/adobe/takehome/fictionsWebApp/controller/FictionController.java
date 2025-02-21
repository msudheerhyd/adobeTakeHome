package com.adobe.takehome.fictionsWebApp.controller;

import com.adobe.takehome.fictionsWebApp.model.Fiction;
import com.adobe.takehome.fictionsWebApp.ratelimit.RateLimited;
import com.adobe.takehome.fictionsWebApp.service.FictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fictions")
public class FictionController {

    private static final Logger logger = LoggerFactory.getLogger(FictionController.class);

    @Autowired
    private FictionService fictionService;

    @GetMapping("/all")
    @RateLimited
    public ResponseEntity<List<Fiction>> getAllFictions() {
        List<Fiction> fictions = fictionService.getAllFictions();
        return ResponseEntity.ok(fictions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Optional<Fiction>> getFictionById(@PathVariable Long id) {
        Optional<Fiction> fiction = fictionService.getFictionById(id);
        return ResponseEntity.ok(fiction);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RateLimited
    public ResponseEntity<Fiction> createFiction(@RequestBody Fiction fiction) {
        Fiction createdFiction = fictionService.createFiction(fiction);
        return ResponseEntity.ok(createdFiction);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Fiction> updateFiction(@PathVariable Long id, @RequestBody Fiction fictionDetails) {
        Fiction updatedFiction = fictionService.updateFiction(fictionDetails.getId(), fictionDetails);
        return ResponseEntity.ok(updatedFiction);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteFiction(@PathVariable Long id) {
        fictionService.deleteFiction(id);
        return ResponseEntity.ok("Successfully delete fiction with id = " + id);
    }
}
