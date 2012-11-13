/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author maykoone
 */
@Controller
public class HomeController {
    
    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String index() {
        return "home/index";
    }
    
    @RequestMapping(value="/error404", method= RequestMethod.GET)
    public String error404(){
        return "errors/error404";
    }
    
    @RequestMapping(value="/{userName}")
    public String userHome(@PathVariable String userName){
        return "redirect:/bookmarks/{userName}";
    }
    
    @RequestMapping(value="/dashboard", method= RequestMethod.GET)
    public String dashboard(Model model){
        String principalUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(principalUserName);
        
        List<Bookmark> followingsBookmarks = bookmarkService.listPublicBookmarksFromFollowingsOfUser(user);
        List<Bookmark> recentBookmarks = bookmarkService.list(1, 15).getContent();
        List<Bookmark> popularBookmarks = bookmarkService.listPopularPublicBookmarks();
        
        model.addAttribute("followingsBookmarks", followingsBookmarks);
        model.addAttribute("recentBookmarks", recentBookmarks);
        model.addAttribute("popularBookmarks", popularBookmarks);
        model.addAttribute("userInstance", user);
        return "home/dashboard";
    }
}
