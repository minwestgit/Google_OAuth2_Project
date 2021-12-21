package com.controller.user.emp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.my.oauth.entity.SessionUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user/emp")
public class UserEmpController
{
  private final HttpSession httpSession;
  

  @RequestMapping(value="/empMasterListView.do", method={RequestMethod.GET, RequestMethod.POST})
  public ModelAndView empMasterListView() throws Exception
  {
    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("LOGIN_SESSION_USER");
    ModelAndView mav = new ModelAndView("user/emp/empMasterListView");
    if (sessionUser != null) mav.addObject("sessionUser", sessionUser);
    
    return mav;
  }
}
