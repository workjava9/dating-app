package com.geomax.datingapp.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity @Table(name="users")
public class User {
  @Id private UUID id = UUID.randomUUID();
  @Column(nullable=false, unique=true) private String email;
  @Column(nullable=false) private String password;
  private String name;
  @Column(name="created_at", nullable=false) private Instant createdAt = Instant.now();

  public User() {}
  public User(String email, String password, String name){
      this.email=email; this.password=password; this.name=name; }


}
