package com.geomax.datingapp.repository;

import com.geomax.datingapp.model.Profile;
import com.geomax.datingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
  Optional<Profile> findByUser(User user);
}
