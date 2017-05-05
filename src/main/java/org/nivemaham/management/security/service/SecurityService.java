package org.nivemaham.management.security.service;

/**
 * Created by nivethika on 4-5-17.
 */
public interface SecurityService {

  String findLoggedInUsername();

  void autologin(String username, String password);

}
