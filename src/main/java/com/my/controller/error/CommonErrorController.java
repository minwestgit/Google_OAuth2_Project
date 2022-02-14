package com.controller.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/common/error")
public class CommonErrorController {
    @RequestMapping(value="error.do", method={RequestMethod.GET, RequestMethod.POST}) 
    public ModelAndView errorView(HttpServletRequest request) throws Exception
    {
        return new ModelAndView("error/error");
    }

    @RequestMapping(value="accessDenied.do", method={RequestMethod.GET, RequestMethod.POST}) 
    public ModelAndView accessDeniedView(HttpServletRequest request) throws Exception
    {
        return new ModelAndView("error/accessDenied");
    }
}
