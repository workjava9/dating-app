package com.geomax.datingapp.controller;
import com.geomax.datingapp.model.Match;
import com.geomax.datingapp.repository.UserRepository;
import com.geomax.datingapp.service.SwipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor(onConstructor=@__(@Autowired))
@RequestMapping("/matches")
public class MatchController {
  private final SwipeService swipes;
  private final UserRepository users;

  @GetMapping
  public ResponseEntity<List<Match>> myMatches(@RequestParam(required=false) UUID userId, Principal principal) {
    UUID id = userId != null ? userId : users.findByEmail(principal.getName()).orElseThrow().getId();
    return ResponseEntity.ok(swipes.matchesFor(id));
  }
}
