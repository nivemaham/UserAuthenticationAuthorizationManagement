package org.nivemaham.management.security.service;

import java.util.HashSet;
import java.util.Set;
import org.nivemaham.management.security.domain.Privilege;
import org.nivemaham.management.security.oauth2.server.MyUserPrincipal;
import org.nivemaham.management.security.repository.UserRepository;
import org.nivemaham.management.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  public MyUserDetailsService() {
    super();
  }

  // API

  @Override
  public UserDetails loadUserByUsername(final String username) {
    final User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Privilege role : user.getPrivileges()){
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }


}
