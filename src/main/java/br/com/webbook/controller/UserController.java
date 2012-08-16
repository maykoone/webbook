/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.User;
import br.com.webbook.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @RequestMapping(value="/create_account", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult result) {
        if(result.hasErrors()){
            return "user/create";
        }
        service.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable Long id) {
        User user = service.findById(id);
        return new ModelAndView("user/show", "user", user);
    }

    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        User user = service.findById(id);
        return new ModelAndView("user/edit", "user", user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String update(User user) {
        service.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        service.remove(service.findById(id));
        return "redirect:/user";
    }
}
