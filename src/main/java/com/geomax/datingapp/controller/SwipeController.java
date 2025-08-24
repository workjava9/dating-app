package com.geomax.datingapp.controller;

import com.geomax.datingapp.repository.UserRepository;
import com.geomax.datingapp.service.SwipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor(onConstructor=@__(@Autowired))
@RequestMapping("/swipes")
public class SwipeController {
  private final SwipeService swipes;
  private final UserRepository users;

  public record SwipeRq(UUID toUserId, boolean like){}

  @PostMapping
  public ResponseEntity<?> swipe(@RequestBody SwipeRq rq, Principal principal){
    var from = users.findByEmail(principal.getName()).orElseThrow();
    boolean matched = swipes.swipe(from.getId(), rq.toUserId(), rq.like());
    return ResponseEntity.ok(Map.of("match", matched));
  }
}
