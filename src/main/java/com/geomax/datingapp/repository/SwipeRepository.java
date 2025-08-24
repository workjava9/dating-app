package com.geomax.datingapp.repository;

import com.geomax.datingapp.model.Swipe;
import com.geomax.datingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SwipeRepository extends JpaRepository<Swipe, UUID> {
    Optional<Swipe> findByFromAndTo(User from, User to);

    boolean existsByFromAndToAndLikedTrue(User from, User to);
}
