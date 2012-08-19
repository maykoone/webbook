/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.User;
import br.com.webbook.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maykoone
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private static final String REDIRECT_USERS = "redirect:/users";
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("user/list", "userList", service.list());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("user/create", "contato", new User());
    }

    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/create";
        }
        service.save(user);
        return REDIRECT_USERS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable Long id) {
        User user = service.findById(id);
        return new ModelAndView("user/show", "user", user);
    }

    @RequestMapping(value = "/account/profile", method = RequestMethod.GET)
    public String edit(Principal principal, Model model) {
        User user = service.findByUserName(principal.getName());
        model.addAttribute("userInstance", user);
        return "user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String update(User user, BindingResult results, Model model) {
        if (results.hasErrors()) {
            return null;
        }
        service.save(user);
        model.addAttribute("message", "O seu perfil foi atualizado com sucesso.");
        return REDIRECT_USERS;
    }

    @RequestMapping(value = "/edit/password", method = RequestMethod.PUT)
    public String changePassword(User user) {
        return REDIRECT_USERS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        service.remove(service.findById(id));
        return REDIRECT_USERS;
    }
}
