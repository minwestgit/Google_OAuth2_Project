package com.my.oauth.oauth;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  MessageSource messageSource;
  
  @Value("${system.url}")
  String systemUrl;
  
  private String exceptionMsgTitleName; // 예외 메시지제목을 REQUEST의 ATTRIBUTE에 저장할 때 사용
  private String exceptionMsgName;      // 예외 메시지를 REQUEST의 ATTRIBUTE에 저장할 때 사용
  private String exceptionEngMsgName;   // 예외 메시지(영문)를 REQUEST의 ATTRIBUTE에 저장할 때 사용
  private String defaultFailureUrl;
  
  public CustomAccessDeniedHandler(String defaultFailureUrl)
  {
    this.exceptionMsgTitleName  = "exceptionMsgTitleName";
    this.exceptionMsgName       = "exceptionMsgName";
    this.exceptionEngMsgName    = "exceptionEngMsgName";
    this.defaultFailureUrl = defaultFailureUrl;
  }
  
  @Override
  public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException exception) throws IOException, ServletException
  {
    logger.debug("■ CustomAccessDeniedHandler.handle : " + exception.getMessage());
    res.setStatus(HttpStatus.FORBIDDEN.value());
    
    String errorMsgTitle = "Error occurred while accessing the page.";
    String errorMsg = messageSource.getMessage("text.invalidAuthority", null, Locale.KOREA);
    String errorEngMsg = messageSource.getMessage("text.invalidAuthority", null, Locale.ENGLISH);
    
    req.setAttribute(exceptionMsgTitleName, errorMsgTitle);
    req.setAttribute(exceptionMsgName, errorMsg);
    req.setAttribute(exceptionEngMsgName, errorEngMsg);
    req.setAttribute("systemUrl", systemUrl);
    
    req.getRequestDispatcher(defaultFailureUrl).forward(req, res);
  }
}
