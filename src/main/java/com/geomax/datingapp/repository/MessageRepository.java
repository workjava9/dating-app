package com.geomax.datingapp.repository;

import com.geomax.datingapp.model.Message;
import com.geomax.datingapp.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
  List<Message> findByMatchOrderByCreatedAtAsc(Match match);
}
