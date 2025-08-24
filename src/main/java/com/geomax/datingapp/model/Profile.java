package com.geomax.datingapp.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(name="profiles")
public class Profile {
  @Id private UUID id = UUID.randomUUID();
  @OneToOne(optional=false) @JoinColumn(name="user_id") private User user;
  @Column(columnDefinition="text") private String bio;
  @Column(columnDefinition="text") private String interests;
  public Profile() {}
  public Profile(User user){ this.user=user; }

}
