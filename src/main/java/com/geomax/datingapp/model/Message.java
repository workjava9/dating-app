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
@Entity @Table(name="messages")
public class Message {
  @Id private UUID id = UUID.randomUUID();
  @ManyToOne(optional=false) @JoinColumn(name="match_id") private Match match;
  @ManyToOne(optional=false) @JoinColumn(name="from_user") private User from;
  @Column(columnDefinition="text", nullable=false) private String text;
  @Column(name="created_at", nullable=false) private Instant createdAt = Instant.now();
  public Message(){}
  public Message(Match match, User from, String text){ this.match=match; this.from=from; this.text=text; }
}
