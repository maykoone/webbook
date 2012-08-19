/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author maykoone
 */
@Controller
public class AuthController {

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        return "auth/login";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        return "redirect:/login?message=login failure";
    }

    @RequestMapping(value = "/logout/success")
    public String logoutSuccess() {
        return "redirect:/home";
    }
    
    @RequestMapping(value="/denied")
    public String accessDenied(){
        return "auth/deniedAccess";
    }
}
