/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.SearchService;
import br.com.webbook.service.UserService;
import br.com.webbook.tags.MessageBean;
import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
        User user = userService.findByUserName(principal.getName());

        Set<Bookmark> bookmarkResults = new HashSet<Bookmark>(searchService.searchBookmarks(q));
        if (bookmarkResults == null || bookmarkResults.isEmpty()) {
            model.addAttribute("messageBookmarks", new MessageBean("Não encontrado nenhum resultado de favorito com esse termo.", MessageBean.TYPE.INFO));
        }
        model.addAttribute("bookmarksResults", bookmarkResults);

        Set<User> userResults = new HashSet<User>(searchService.searchUsers(q));
        if (userResults == null || userResults.isEmpty()) {
            model.addAttribute("messageUsers", new MessageBean("Não encontrado nenhum resultado de usuário com esse termo.", MessageBean.TYPE.INFO));
        }
        model.addAttribute("userResults", userResults);

        model.addAttribute("userInstance", user);
        model.addAttribute("querySearch", q);

        return "search/list";
    }

    @RequestMapping(value = "/ranking/{userName}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Long> getTagRanking(@PathVariable String userName) {
        return searchService.countTagsByUser(userName);
    }
}
