package com.lotteon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonController {

    @Value(value = "${spring.application.name}")
    private String appName;

    @Value(value = "${spring.application.version}")
    private String appVersion;

    @ModelAttribute
    public void getCommon(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("appVersion", appVersion);
    }

    public void getAuthentication(Authentication auth, Model model) {
        if(auth != null && auth.getPrincipal() != null) {
            model.addAttribute("user", auth.getPrincipal());
        }else{
            model.addAttribute("user",null);
        }
    }
}
