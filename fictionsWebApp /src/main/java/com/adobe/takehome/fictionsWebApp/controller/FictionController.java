package com.adobe.takehome.fictionsWebApp.controller;

import com.adobe.takehome.fictionsWebApp.model.Fiction;
import com.adobe.takehome.fictionsWebApp.service.FictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fictions")
public class FictionController {

    @Autowired
    private FictionService fictionService;

    @GetMapping
    public List<Fiction> getAllFictions() {
        return fictionService.getAllFictions();
    }

    @GetMapping("/{id}")
    public Fiction getFictionById(@PathVariable Long id) {
        return fictionService.getFictionById(id)
                .orElseThrow(() -> new RuntimeException("Fiction not found"));
    }

    @PostMapping
    public Fiction createFiction(@RequestBody Fiction fiction) {
        return fictionService.createFiction(fiction);
    }

    @PutMapping("/{id}")
    public Fiction updateFiction(@PathVariable Long id, @RequestBody Fiction fictionDetails) {
        return fictionService.updateFiction(id, fictionDetails);
    }

    @DeleteMapping("/id")
    public void deleteFiction(@PathVariable Long id) {
        fictionService.deleteFiction(id);
    }

}
