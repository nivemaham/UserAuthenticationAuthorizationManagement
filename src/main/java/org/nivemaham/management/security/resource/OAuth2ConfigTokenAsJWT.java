//package org.radarcns.security.resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//
////@Configuration
////@EnableAuthorizationServer // [1]
//public class OAuth2ConfigTokenAsJWT extends AuthorizationServerConfigurerAdapter {
//
//  @Autowired
//  private Environment environment;
//
//  @Autowired
////  @Qualifier("authenticationManagerBean")
//  private AuthenticationManager authenticationManager;
//
//  @Override // [2]
//  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer()).authenticationManager(authenticationManager);
//  }
//
//  @Bean
//  protected JwtAccessTokenConverter jwtTokenEnhancer() {
//    String pwd = environment.getProperty("keystore.password");
//    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
//        new ClassPathResource("keystore.jks"),
//        pwd.toCharArray());
//    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
//    return converter;
//  }
//
//  @Bean
//  public TokenStore tokenStore() {
//    return new JwtTokenStore(jwtTokenEnhancer());
//  }
//
//  @Override
//  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//    security.tokenKeyAccess("permitAll()")
//        .checkTokenAccess("isAuthenticated()");
//  }
//  @Override // [3]
//  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.inMemory()
//        .withClient("service-account-1")
//        .secret("service-account-1-secret")
//        .authorizedGrantTypes("client_credentials", "password", "implicit", "refresh_token",
//            "authorization_code")
//        .scopes("resource-server-read", "resource-server-write")
//        .authorities("ROLE_RS_READ");
////        .autoApprove(true);
//    // @formatter:on
//  }
//
//}