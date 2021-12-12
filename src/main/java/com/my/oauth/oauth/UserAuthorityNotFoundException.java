package com.my.oauth.oauth;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class UserAuthorityNotFoundException extends AuthenticationException
{
  public UserAuthorityNotFoundException(String msg)
  {
    super(msg);
  }
}
