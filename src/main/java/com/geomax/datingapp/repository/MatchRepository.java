package com.geomax.datingapp.repository;

import com.geomax.datingapp.model.Match;
import com.geomax.datingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {
  List<Match> findByUser1OrUser2(User u1, User u2);
  boolean existsByUser1AndUser2(User u1, User u2);
}
