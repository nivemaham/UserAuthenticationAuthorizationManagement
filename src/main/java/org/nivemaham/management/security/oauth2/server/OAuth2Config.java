package org.nivemaham.management.security.oauth2.server;

import javax.sql.DataSource;
import org.nivemaham.management.security.service.MyUserDetailsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer // [1]
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

  private final org.slf4j.Logger log = LoggerFactory.getLogger(OAuth2Config.class);
  @Autowired
  private Environment environment;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private MyUserDetailsService userDetailsService;

  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


  @Autowired
//  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;

  @Override // [2]
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer())
        .authenticationManager(authenticationManager);
  }

  @Bean
  protected JwtAccessTokenConverter jwtTokenEnhancer() {
    String pwd = environment.getProperty("keystore.password");
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
        new ClassPathResource("keystore.jks"),
        pwd.toCharArray());
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
    return converter;
  }

  @Bean
  public TokenStore tokenStore() {
//    return new JwtTokenStore(jwtTokenEnhancer());
    return new JdbcTokenStore(dataSource);
  }

  @Bean
  protected AuthorizationCodeServices authorizationCodeServices() {
    return new JdbcAuthorizationCodeServices(dataSource);
  }


  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//    security.tokenKeyAccess("permitAll()")
//        .checkTokenAccess("isAuthenticated()");
    security.passwordEncoder(passwordEncoder);
  }

  @Override // [3]
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.inMemory()
    clients.jdbc(dataSource)
        .passwordEncoder(passwordEncoder)
        .withClient("myclient-id")
        .secret("myclient-secret")
        .authorizedGrantTypes("client_credentials", "password", "implicit", "refresh_token",
            "authorization_code")
        .scopes("resource-server-read", "resource-server-write")
        .authorities("ROLE_RS_READ")
        .autoApprove(true);
//        .autoApprove(true);
    // @formatter:on
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Autowired
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
    auth.authenticationProvider(authenticationProvider());
    // @formatter:on
  }
}