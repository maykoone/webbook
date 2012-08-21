/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.tags.MessageBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author maykoone
 */
@Controller
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class.getName());

    @RequestMapping("/login")
    public String login() {
        LOG.log(Level.INFO, "Request for login page");
        return "auth/login";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure(RedirectAttributes redirectAttributes) {
        LOG.log(Level.INFO, "Request for login failure page");
        redirectAttributes.addFlashAttribute("message", new MessageBean("Não foi possível realizar login. Por favor verificar nome de usuário ou senha.", MessageBean.TYPE.ERROR));
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout/success")
    public String logoutSuccess() {
        LOG.log(Level.INFO, "Request for logout action");
        return "redirect:/home";
    }

    @RequestMapping(value = "/denied")
    public String accessDenied() {
        LOG.log(Level.INFO, "Acccess denied");
        return "auth/deniedAccess";
    }
}
