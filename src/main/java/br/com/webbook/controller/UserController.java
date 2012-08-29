/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.User;
import br.com.webbook.service.UserService;
import br.com.webbook.tags.MessageBean;
import br.com.webbook.validation.ProfileChecks;
import br.com.webbook.web.form.UserChangePasswordForm;
import java.security.Principal;
import javax.validation.Valid;
import javax.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String list() {
        return "redirect:/bookmarks";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("user/create", "contato", new User());
    }

    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public String save(@Validated({Default.class}) User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "user/create";
        }
        service.save(user);
        attributes.addFlashAttribute("message", new MessageBean("Seu cadastro foi realizado com sucesso. Efetue o login para acesso."));
        return "redirect:/login";
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
        model.addAttribute("userChangePassword", new UserChangePasswordForm());
        return "user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String update(@Validated({ProfileChecks.class}) User userForm, BindingResult results, Principal principal, RedirectAttributes redirectAttributes) {
        //carrega os dados do usuário logado.
        User user = service.findByUserName(principal.getName());
//        if (ValidationUtils.isValid(results, user, ProfileChecks.class)) {
        if (!results.hasErrors()) {
            //evitar que altere outro usuário
            if (user != null) {
                user.setName(userForm.getName());
                user.setEmail(userForm.getEmail());
                user.setLastName(userForm.getLastName());

                service.editProfile(user);
                redirectAttributes.addFlashAttribute("message", new MessageBean("O seu perfil foi atualizado com sucesso.", MessageBean.TYPE.SUCESS));

            } else {
                redirectAttributes.addFlashAttribute("message", new MessageBean("Não foi possível alterar os dados.", MessageBean.TYPE.ERROR));
            }
        }
        return REDIRECT_USERS + "/account/profile";

    }

    @RequestMapping(value = "/edit/password", method = RequestMethod.PUT)
    public String changePassword(@Valid UserChangePasswordForm userChangePassword, BindingResult results, RedirectAttributes redirectAttributes, Principal principal) {
        if (!results.hasErrors()) {
            if (service.changePassword(principal.getName(), userChangePassword.getOldPassword(), userChangePassword.getNewPassword())) {
                redirectAttributes.addFlashAttribute("message", new MessageBean("Sua senha foi alterada com sucesso", MessageBean.TYPE.SUCESS));

            } else {
                redirectAttributes.addFlashAttribute("message", new MessageBean("Não foi possível alterar a senha. Verifique se você digitou a senha atual corretamente.", MessageBean.TYPE.ERROR));
            }
        } else {
            return "user/edit";
        }
        return REDIRECT_USERS + "/account/profile";
    }

    @RequestMapping(value = "/{userName}/follow", method = RequestMethod.GET)
    public String createFriendship(@PathVariable String userName, Principal principal, RedirectAttributes attributes) {
        User user = service.findByUserName(principal.getName());
        User followed = service.findByUserName(userName);

        if (followed == null) {
            return "redirect:/error404";
        }

        if (!service.follow(user, followed)) {
            return "redirect:/denied";
        }

        attributes.addFlashAttribute("message", new MessageBean("você está seguindo " + followed.getUserName() + " agora", MessageBean.TYPE.SUCESS));
        return REDIRECT_USERS;
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    public String getFollowers(Principal principal, Model model) {
        User user = service.findByUserName(principal.getName());
        model.addAttribute("followers", user.getFollowers());
        return "user/followers";
    }

    @RequestMapping(value = "/followings", method = RequestMethod.GET)
    public String getFollowings(Principal principal, Model model) {
        User user = service.findByUserName(principal.getName());
        model.addAttribute("followings", user.getFollowings());
        return "user/followings";
    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String delete(@PathVariable Long id) {
//        service.remove(service.findById(id));
//        return REDIRECT_USERS;
//    }
}
