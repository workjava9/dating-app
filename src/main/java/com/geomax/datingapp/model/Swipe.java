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
@Entity @Table(name="swipes",
  uniqueConstraints=@UniqueConstraint(columnNames={"from_user","to_user"}))
public class Swipe {
  @Id private UUID id = UUID.randomUUID();
  @ManyToOne(optional=false) @JoinColumn(name="from_user") private User from;
  @ManyToOne(optional=false) @JoinColumn(name="to_user") private User to;
  private boolean liked;
  @Column(name="created_at", nullable=false) private Instant createdAt = Instant.now();

  public Swipe(){}
  public Swipe(User from, User to, boolean liked){ this.from=from; this.to=to; this.liked=liked; }

}
