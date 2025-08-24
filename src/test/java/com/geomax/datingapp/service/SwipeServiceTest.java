package com.geomax.datingapp.service;

import com.geomax.datingapp.model.Match;
import com.geomax.datingapp.model.Swipe;
import com.geomax.datingapp.model.User;
import com.geomax.datingapp.repository.MatchRepository;
import com.geomax.datingapp.repository.SwipeRepository;
import com.geomax.datingapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SwipeServiceTest {
  @Mock SwipeRepository swipes;
  @Mock MatchRepository matches;
  @Mock UserRepository users;
  @InjectMocks SwipeService svc;

  @Test
  void likeCreatesMatchWhenMutual(){
    var a = new User("a@a","pwd","A"); a.setId(UUID.randomUUID());
    var b = new User("b@b","pwd","B"); b.setId(UUID.randomUUID());
    when(users.findById(a.getId())).thenReturn(Optional.of(a));
    when(users.findById(b.getId())).thenReturn(Optional.of(b));
    when(swipes.findByFromAndTo(a,b)).thenReturn(Optional.empty());
    when(swipes.existsByFromAndToAndLikedTrue(b,a)).thenReturn(true);
    when(matches.existsByUser1AndUser2(a,b)).thenReturn(false);
    when(matches.existsByUser1AndUser2(b,a)).thenReturn(false);

    boolean matched = svc.swipe(a.getId(), b.getId(), true);

    verify(swipes).save(any(Swipe.class));
    verify(matches).save(any(Match.class));
    assertThat(matched).isTrue();
  }

  @Test
  void dislikeDoesNotMatch(){
    var a = new User("a@a","pwd","A"); a.setId(UUID.randomUUID());
    var b = new User("b@b","pwd","B"); b.setId(UUID.randomUUID());
    when(users.findById(a.getId())).thenReturn(Optional.of(a));
    when(users.findById(b.getId())).thenReturn(Optional.of(b));
    when(swipes.findByFromAndTo(a,b)).thenReturn(Optional.empty());

    boolean matched = svc.swipe(a.getId(), b.getId(), false);

    verify(matches, never()).save(any());
    assertThat(matched).isFalse();
  }
}
