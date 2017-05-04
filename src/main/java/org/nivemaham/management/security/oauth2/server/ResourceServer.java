package org.nivemaham.management.security.oauth2.server;

import javax.sql.DataSource;
import org.nivemaham.management.security.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer // [2]
public class ResourceServer extends ResourceServerConfigurerAdapter {

//  @Inject
//  TokenStore tokenStore;
  @Autowired
  private DataSource dataSource;

  @Override // [3]
  public void configure(HttpSecurity http) throws Exception {
    // @formatter:off
//    http
//        // Just for laughs, apply OAuth protection to only 2 resources
//        .requestMatchers().antMatchers("/", "/admin/beans").and()
//        .authorizeRequests()
//        .anyRequest().access("#oauth2.hasScope('resource-server-read')")
//        .antMatchers("/write").hasAuthority("ROLE_RS_READ");
    http.authorizeRequests().anyRequest().authenticated();
        ; //[4]
    // @formatter:on
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(Application.RESOURCE_ID).tokenStore(tokenStore());
  }

  @Bean
  public TokenStore tokenStore() {
    return new JdbcTokenStore(dataSource);
  }
}