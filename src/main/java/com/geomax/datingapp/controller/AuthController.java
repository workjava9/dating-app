package com.geomax.datingapp.controller;
import com.geomax.datingapp.security.JwtService;
import com.geomax.datingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.geomax.datingapp.repository.UserRepository;

import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor=@__(@Autowired))
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService users;
    private final JwtService jwt;
    private final UserRepository repo;
    private final PasswordEncoder enc;

  public record RegisterRq(String email, String password, String name){}
  public record LoginRq(String email, String password){}

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRq rq){
    var u = users.register(rq.email(), rq.password(), rq.name());
    var token = jwt.generateToken(User.withUsername(u.getEmail()).password(u.getPassword()).authorities("USER").build());
    return ResponseEntity.ok(Map.of("email", u.getEmail(), "token", token));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRq rq){
    var u = repo.findByEmail(rq.email()).orElseThrow();
    if (!enc.matches(rq.password(), u.getPassword())) return ResponseEntity.status(401).build();
    var token = jwt.generateToken(User.withUsername(u.getEmail()).password(u.getPassword()).authorities("USER").build());
    return ResponseEntity.ok(Map.of("email", u.getEmail(), "token", token));
  }
}
