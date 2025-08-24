package com.geomax.datingapp.model;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name="matches")
public class Match {
  @Id private UUID id = UUID.randomUUID();
  @ManyToOne(optional=false) @JoinColumn(name="user1") private User user1;
  @ManyToOne(optional=false) @JoinColumn(name="user2") private User user2;
  @Column(name="created_at", nullable=false) private Instant createdAt = Instant.now();
  public Match(){}
  public Match(User u1, User u2){ this.user1=u1; this.user2=u2; }
}
