package org.nivemaham.management.security.service;

import java.util.HashSet;
import org.nivemaham.management.security.domain.User;
import org.nivemaham.management.security.repository.PrivilegeRepository;
import org.nivemaham.management.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by nivethika on 4-5-17.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PrivilegeRepository roleRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public void save(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setPrivileges(new HashSet<>(roleRepository.findAll()));
    userRepository.save(user);
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

}
