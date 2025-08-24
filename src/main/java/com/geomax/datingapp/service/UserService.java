package com.geomax.datingapp.service;

import com.geomax.datingapp.model.Profile;
import com.geomax.datingapp.model.User;
import com.geomax.datingapp.repository.ProfileRepository;
import com.geomax.datingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserService {
  private final UserRepository users;
  private final ProfileRepository profiles;
  private final PasswordEncoder encoder;

  public User register(String email, String password, String name){
    Optional<User> exist = users.findByEmail(email);
    if (exist.isPresent()) {
      return users.save(exist.get());
    }
    User u = new User(email, encoder.encode(password), name);
    u = users.save(u);
    profiles.save(new Profile(u));
    return u;
  }
}
