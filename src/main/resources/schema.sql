drop table if exists users;
create table users (
  username varchar(256),
  password varchar(256),
  enabled boolean
);

drop table if exists authorities;
create table authorities (
  username varchar(256),
  authority varchar(256)
);

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(256),
  token VARCHAR(256),
  authentication_id VARCHAR(4096),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token VARCHAR(4096),
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication VARCHAR(4096),
  refresh_token VARCHAR(256)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token VARCHAR(4096),
  authentication VARCHAR(4096)
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication VARCHAR(4096)
);

