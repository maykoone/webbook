/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.UserService;
import br.com.webbook.service.impl.UserDetailsAdapter;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maykoone
 */
@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(required = false) Integer page, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        Page<Bookmark> pageResult = bookmarkService.listByUser(user, page == null ? 1 : page, 10);

        ModelAndView model = new ModelAndView("bookmark/list", "bookmarkList", pageResult);
        model.addObject("bookmark", new Bookmark());
        //pagination
        int current = pageResult.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, pageResult.getTotalPages());

        model.addObject("deploymentLog", page);
        model.addObject("beginIndex", begin);
        model.addObject("endIndex", end);
        model.addObject("currentIndex", current);
        return model;
    }

    @RequestMapping( method = RequestMethod.POST)
    public String save(@Valid Bookmark bookmark, BindingResult result) {
        User user = loadCurrentUser();

        if (result.hasErrors()) {
            return "bookmark/list";
        }
        bookmark.setUser(user);
        bookmarkService.save(bookmark);
        return "redirect:/bookmarks";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Bookmark edit(@PathVariable Long id) {
        UserDetailsAdapter userDetails = (UserDetailsAdapter) ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());

        Bookmark bookmark = bookmarkService.findById(id);

        if (!bookmark.getUser().getId().equals(userDetails.getId())) {
            return null;
        }
        return bookmark;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        UserDetailsAdapter userDetails = (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getDetails();

        Bookmark bookmark = bookmarkService.findById(id);
        if (!bookmark.getUser().getId().equals(userDetails.getId())) {
            return null;
        }
        bookmarkService.remove(bookmark);
        return "redirect:/bookmarks";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String destroy(@PathVariable Long id) {
        UserDetailsAdapter userDetails = (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getDetails();

        Bookmark bookmark = bookmarkService.findById(id);
        if (!bookmark.getUser().getId().equals(userDetails.getId())) {
            return null;
        }
        bookmarkService.remove(bookmark);
        return "sucess";
    }

    private User loadCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        return user;
    }
}
