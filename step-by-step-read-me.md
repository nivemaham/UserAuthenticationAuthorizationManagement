
//use everything from spring
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("service-account-1")
        .secret("service-account-1-secret")
        .authorizedGrantTypes("client_credentials")
        .scopes("resource-server-read", "resource-server-write");
  }
}

//request token
curl service-account-1:service-account-1-secret@localhost:8080/oauth/token -d grant_type=client_credentials

//validate token

curl -H "Authorization: Bearer $TOKEN" localhost:9000