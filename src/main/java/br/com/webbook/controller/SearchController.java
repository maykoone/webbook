/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.SearchService;
import br.com.webbook.service.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    public String search(@RequestParam String q, Principal principal, Model model) {
        return null;
    }

    @RequestMapping(value = "/ranking/{userName}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Long> getTagRanking(@PathVariable String userName) {
        return searchService.tagRankingByUser(userName);
    }
}
