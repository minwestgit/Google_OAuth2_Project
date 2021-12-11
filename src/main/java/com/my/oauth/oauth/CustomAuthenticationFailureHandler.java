package com.my.oauth.oauth;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  MessageSource messageSource;
  
  @Value("${system.url}")
  String systemUrl;
  
  private String loginRedirectUrl;      // 로그인 성공시 redirect 할 URL이 지정되어 있는 input태그 name
  private String exceptionMsgTitleName; // 예외 메시지제목을 REQUEST의 ATTRIBUTE에 저장할 때 사용
  private String exceptionMsgName;      // 예외 메시지를 REQUEST의 ATTRIBUTE에 저장할 때 사용
  private String exceptionEngMsgName;   // 예외 메시지(영문)를 REQUEST의 ATTRIBUTE에 저장할 때 사용
  private String defaultFailureUrl;     // 화면에 보여줄 url
  
  public CustomAuthenticationFailureHandler(String loginRedirectUrl, String exceptionMsgTItleName, String exceptionMsgName, String exceptionEngMsgName, String defaultFailureUrl)
  {
    this.loginRedirectUrl       = loginRedirectUrl;
    this.exceptionMsgTitleName  = exceptionMsgTItleName;
    this.exceptionMsgName       = exceptionMsgName;
    this.exceptionEngMsgName    = exceptionEngMsgName;
    this.defaultFailureUrl      = defaultFailureUrl;
  }
  
  public CustomAuthenticationFailureHandler(String defaultFailureUrl)
  {
    this.loginRedirectUrl       = "loginRedirectUrl";
    this.exceptionMsgTitleName  = "exceptionMsgTitleName";
    this.exceptionMsgName       = "exceptionMsgName";
    this.exceptionEngMsgName    = "exceptionEngMsgName";
    this.defaultFailureUrl      = defaultFailureUrl;
  }
  
  public String getLoginRedirectUrl()
  {
    return loginRedirectUrl;
  }

  public void setLoginRedirectUrl(String loginRedirectUrl)
  {
    this.loginRedirectUrl = loginRedirectUrl;
  }

  public String getExceptionMsgName()
  {
    return exceptionMsgName;
  }

  public void setExceptionMsgName(String exceptionMsgName)
  {
    this.exceptionMsgName = exceptionMsgName;
  }

  public String getDefaultFailureUrl()
  {
    return defaultFailureUrl;
  }

  public void setDefaultFailureUrl(String defaultFailureUrl)
  {
    this.defaultFailureUrl = defaultFailureUrl;
  }
  
  @Override
  public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException
  {
    logger.debug("■ CustomAuthenticationFailureHandler.onAuthenticationFailure : " + exception.getMessage());
    
    String loginRedirect = req.getParameter(loginRedirectUrl);
    String errorMsgTitle = "Error occurred during the login process.";
    String errorMsg = exception.getMessage();
    String errorEngMsg = exception.getMessage();
    
    if (exception instanceof UsernameNotFoundException)
    {
      errorMsg = messageSource.getMessage("DigestAuthenticationFilter.usernameNotFound", null, Locale.KOREA);
      errorEngMsg = messageSource.getMessage("DigestAuthenticationFilter.usernameNotFound", null, Locale.ENGLISH);
    }
    else if (exception instanceof UserAuthorityNotFoundException)
    {
      errorMsg = messageSource.getMessage("text.userauthorityNotFoundException", null, Locale.KOREA);
      errorEngMsg = messageSource.getMessage("text.userauthorityNotFoundException", null, Locale.ENGLISH);
    }
    else if (exception instanceof InternalAuthenticationServiceException)
    {
      errorMsg = messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.InternalAuthentication", null, Locale.KOREA);
      errorEngMsg = messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.InternalAuthentication", null, Locale.ENGLISH);
    } 
    else if (exception instanceof InvalidCompanyException)
    {
      errorMsg = messageSource.getMessage("text.invalidCompanyException", null, Locale.KOREA);
      errorEngMsg = messageSource.getMessage("text.invalidCompanyException", null, Locale.ENGLISH);
    }
    
    req.setAttribute(loginRedirectUrl, loginRedirect);
    req.setAttribute(exceptionMsgTitleName, errorMsgTitle);
    req.setAttribute(exceptionMsgName, errorMsg);
    req.setAttribute(exceptionEngMsgName, errorEngMsg);
    req.setAttribute("systemUrl", systemUrl);
    
    req.getRequestDispatcher(defaultFailureUrl).forward(req, res);
  }
}
