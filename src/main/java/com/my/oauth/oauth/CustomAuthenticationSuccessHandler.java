package com.my.oauth.oauth;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.oauth.entity.SessionUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.LocaleResolver;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private String username;
  private String defaultUrl;
  private RequestCache requestCache = new HttpSessionRequestCache();
  private RedirectStrategy redirectStragtegy = new DefaultRedirectStrategy();
  
  @Autowired
  LocaleResolver localeResolver;
  
  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getDefaultUrl()
  {
    return defaultUrl;
  }

  public void setDefaultUrl(String defaultUrl)
  {
    this.defaultUrl = defaultUrl;
  }
  
  // Constructor
  public CustomAuthenticationSuccessHandler(String username, String defaultUrl)
  {
    this.username = username;
    this.defaultUrl = defaultUrl;
  }
  
  // Constructor
  public CustomAuthenticationSuccessHandler(String defaultUrl)
  {
    this.defaultUrl = defaultUrl;
  }
  
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
  {
    logger.debug("■ CustomAuthenticationSuccessHandler.onAuthenticationSuccess > authentication.getPrincipal() : " + authentication.getPrincipal());
    WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
    
    // 세션에 저장된 언어코드 기준으로 로케일 변경
    SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("LOGIN_SESSION_USER");
    String sessionLangCd = sessionUser.getLanguageCd();
    switch (sessionLangCd)
    {
      case "ko-KR":
        localeResolver.setLocale(request, response, Locale.KOREA);
        break;
      case "en-US":
        localeResolver.setLocale(request, response, Locale.US);
        break;
      default:
        break;
    }
    
    // 에러세션 지우기
    clearAuthenticationAttributes(request);
    
    // Redirect URL 작업.
    resultRedirectStrategy(request, response, authentication);
  }
  
  // redirectUrl 지정 메서드
  protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
  {
    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest != null)
    {
      String targetUrl = savedRequest.getRedirectUrl();
      logger.debug("◆ savedRequest1.getRedirectUrl :" + targetUrl);
      redirectStragtegy.sendRedirect(request, response, targetUrl);
    } 
    else
    {
      logger.debug("◆ savedRequest2.getRedirectUrl :" + defaultUrl);
      redirectStragtegy.sendRedirect(request, response, defaultUrl);
    }
  }
  
  // 남아있는 에러세션이 있다면 지워준다.
  protected void clearAuthenticationAttributes(HttpServletRequest request)
  {
    HttpSession session = request.getSession(false);
    if (session == null) return;
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }
}
