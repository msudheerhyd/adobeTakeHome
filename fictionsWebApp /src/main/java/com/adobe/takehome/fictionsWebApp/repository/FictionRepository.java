package com.adobe.takehome.fictionsWebApp.repository;

import com.adobe.takehome.fictionsWebApp.model.Fiction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FictionRepository extends JpaRepository<Fiction, Long> {
}
