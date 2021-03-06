package com.my.oauth.oauth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.my.oauth.entity.EmpAuthority;
import com.my.oauth.entity.EmpMaster;
import com.my.oauth.entity.OAuthAttributes;
import com.my.oauth.entity.SessionUser;
import com.my.oauth.repository.EmpAuthorityRepository;
import com.my.oauth.repository.EmpMasterRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
{
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final EmpMasterRepository empMasterRepository;
  private final EmpAuthorityRepository empAuthorityRepository;
  private final HttpSession httpSession;

  @Autowired
	public CustomOAuth2UserService(
    EmpMasterRepository empMasterRepository, 
    EmpAuthorityRepository empAuthorityRepository, 
    HttpSession httpSession
  ) {
    
		this.empMasterRepository = empMasterRepository;
    this.empAuthorityRepository = empAuthorityRepository;
    this.httpSession = httpSession;
	}
  
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
  {
    logger.debug("??? CustomOAuth2UserService.loadUser.");
    
    OAuth2UserService delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);
    List<EmpAuthority> empAuthorities = new ArrayList<>();
    Set<GrantedAuthority> authorities = new HashSet<>();
    
    // ?????? ????????? ???????????? ???????????? ???????????? ??????(ex. ??????, ????????? ??????)
    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    // OAuth2 ????????? ?????? ??? ???????????? ?????????. PK??? ?????? ??????, ????????? ?????? ?????? sub??? ??????
    String userNameAttributedName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    // OAuth2UserService??? ?????? ????????? OAuth2User??? attribute??? ?????? ?????????
    OAuthAttributes attributes = OAuthAttributes.of(userNameAttributedName, oAuth2User.getAttributes());
    
    // ????????? ???????????? ?????? 
    Optional<EmpMaster> empMaster = empMasterRepository.findByEmail(attributes.getEmail());
    
    // ???????????? ?????????
    if (empMaster.isPresent())
    {
      // ????????? ?????? ?????? ??????????????? ?????? ?????? ?????? ??????.
      if (!"Y".equals(empMaster.get().getCompanyMaster().getUseYn())) 
        throw new InvalidCompanyException("User Company Invalid Exception");
      
      // ????????? ROLE ?????? ?????? (????????? ??????) ??? ????????? ?????? ?????? ?????? ??????
      empAuthorities = empAuthorityRepository.findByEmail(attributes.getEmail());
      
      if (empAuthorities == null || empAuthorities.isEmpty())
        throw new UserAuthorityNotFoundException("User Authority Not Found Exception");  
      else
      {
        for(int i = 0 ; i < empAuthorities.size() ; i++) 
          authorities.add(new SimpleGrantedAuthority(empAuthorities.get(i).getAuthorityName()));
      }
      
      // ????????? ????????? ????????? ?????? ?????? ?????? ??? ????????? ??????
      SessionUser sessionUser = new SessionUser(empMaster);
      httpSession.setAttribute("LOGIN_SESSION_USER", sessionUser);
    }
    else
    {
      throw new UsernameNotFoundException("User Email Not Found Exception");
    }

    return new DefaultOAuth2User(authorities, attributes.getAttributes(), attributes.getNameAttributeKey());
  }
}