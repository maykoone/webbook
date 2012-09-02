/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author maykoone
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String search(@RequestParam String q, Principal principal, Model model) {
        return null;
    }
}
