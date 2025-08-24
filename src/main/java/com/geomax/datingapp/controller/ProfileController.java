package com.geomax.datingapp.controller;

import com.geomax.datingapp.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@AllArgsConstructor(onConstructor=@__(@Autowired))
@RequestMapping("/profiles")
public class ProfileController {
  private final ProfileRepository profiles;

  @GetMapping("/{id}")
  public ResponseEntity<?> get(@PathVariable UUID id){
    return profiles.findById(id).<ResponseEntity<?>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
