package com.adobe.takehome.fictionsWebApp.service;

import com.adobe.takehome.fictionsWebApp.model.Fiction;
import com.adobe.takehome.fictionsWebApp.repository.FictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FictionService {

    @Autowired
    private FictionRepository fictionRepository;

    public List<Fiction> getAllFictions() {
        return fictionRepository.findAll();
    }

    public Optional<Fiction> getFictionById(Long id) {
        return fictionRepository.findById(id);
    }

    public Fiction createFiction(Fiction fiction) {
        return fictionRepository.save(fiction);
    }

    public Fiction updateFiction(Long id, Fiction fictionDetails) {
        Fiction fiction = fictionRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Fiction not found"));
        fiction.setTitle(fictionDetails.getTitle());
        fiction.setAuthor(fictionDetails.getAuthor());
        return fictionRepository.save(fiction);
    }

    public void deleteFiction(Long id) {
        fictionRepository.deleteById(id);
    }
}
