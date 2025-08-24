package com.geomax.datingapp.service;

import com.geomax.datingapp.model.Match;
import com.geomax.datingapp.model.Swipe;
import com.geomax.datingapp.model.User;
import com.geomax.datingapp.repository.MatchRepository;
import com.geomax.datingapp.repository.SwipeRepository;
import com.geomax.datingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class SwipeService {
  private final SwipeRepository swipes;
  private final MatchRepository matches;
  private final UserRepository users;

  public boolean swipe(UUID fromId, UUID toId, boolean liked){
    User from = users.findById(fromId).orElseThrow();
    User to = users.findById(toId).orElseThrow();
    Swipe s = swipes.findByFromAndTo(from,to).orElse(new Swipe(from,to,liked));
    s.setLiked(liked);
    swipes.save(s);

    if (liked && swipes.existsByFromAndToAndLikedTrue(to, from)) {
      if (!matches.existsByUser1AndUser2(from, to) && !matches.existsByUser1AndUser2(to, from)) {
        matches.save(new Match(from, to));
      }
      return true;
    }
    return false;
  }

  public List<Match> matchesFor(UUID userId){
    User u = users.findById(userId).orElseThrow();
    return matches.findByUser1OrUser2(u,u);
  }
}
