package org.nivemaham.management.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
public class Application {

  public static final String RESOURCE_ID = "blog_resource";

  private String message = "Hello world!";

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
//    SetupData setupData = new SetupData();
//    setupData.init();
  }

  @RequestMapping("/") //[1]
  public String home() {
    return message + "\n";
  }

//  @PreAuthorize("hasRole('ROLE_RS_READ')")
  @RequestMapping(value = "/write", method = RequestMethod.POST)
  public void updateMessage(@RequestBody String message) {
    this.message = message;
  }


}


