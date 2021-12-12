package com.my.oauth.oauth;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class InvalidCompanyException extends AuthenticationException
{
  public InvalidCompanyException(String msg)
  {
    super(msg);
  }
}