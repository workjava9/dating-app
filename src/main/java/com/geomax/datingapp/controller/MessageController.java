package com.geomax.datingapp.controller;

import com.geomax.datingapp.model.Match;
import com.geomax.datingapp.model.Message;
import com.geomax.datingapp.repository.MatchRepository;
import com.geomax.datingapp.repository.MessageRepository;
import com.geomax.datingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor(onConstructor=@__(@Autowired))
@RequestMapping("/matches/{matchId}/messages")
public class MessageController {
  private final MessageRepository messages;
  private final MatchRepository matches;
  private final UserRepository users;

  @GetMapping
  public ResponseEntity<List<Message>> list(@PathVariable UUID matchId, Principal principal) {
    var me = users.findByEmail(principal.getName()).orElseThrow();
    Match m = matches.findById(matchId).orElseThrow();
    if (!m.getUser1().getId().equals(me.getId()) && !m.getUser2().getId().equals(me.getId())) {
      return ResponseEntity.status(403).build();
    }
    return ResponseEntity.ok(messages.findByMatchOrderByCreatedAtAsc(m));
  }

  public record SendRq(String text){}

  @PostMapping
  public ResponseEntity<?> send(@PathVariable UUID matchId, @RequestBody SendRq rq, Principal principal) {
    var me = users.findByEmail(principal.getName()).orElseThrow();
    Match m = matches.findById(matchId).orElseThrow();
    if (!m.getUser1().getId().equals(me.getId()) && !m.getUser2().getId().equals(me.getId())) {
      return ResponseEntity.status(403).build();
    }
    var saved = messages.save(new Message(m, me, rq.text()));
    return ResponseEntity.ok(Map.of("id", saved.getId(), "text", saved.getText()));
  }
}
