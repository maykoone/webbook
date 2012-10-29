/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.FilterService;
import br.com.webbook.service.SearchService;
import br.com.webbook.service.UserService;
import br.com.webbook.support.scraping.WebScraper;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private SearchService searchService;
    @Autowired
    private FilterService filterService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(required = false) Integer page, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        Page<Bookmark> pageResult = bookmarkService.listByUser(user, page == null ? 1 : page, 10);

        ModelAndView model = new ModelAndView("bookmark/list");
        model.addObject("bookmark", new Bookmark());
        model.addAllObjects(configurePagination(pageResult));
        model.addObject("userInstance", user);
        model.addObject("filterCount", filterService.countByUser(user));
        return model;
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public String listPublic(@PathVariable String userName, Model model, @RequestParam(required = false) Integer page, Principal principal) {
        if (userName.equals(principal.getName())) {
            return "redirect:/bookmarks";
        }
        User userSearch = userService.findByUserName(userName);
        if (userSearch == null) {
            return "redirect:/error404";
        }
        User userInstance = userService.findByUserName(principal.getName());

        Page<Bookmark> pageResult = bookmarkService.listPublicBookmarksByUser(userSearch, page == null ? 1 : page, 10);
        model.addAttribute("userSearch", userSearch);
        model.addAttribute("userInstance", userInstance);
        model.addAttribute("bookmark", new Bookmark());
        model.addAllAttributes(configurePagination(pageResult));

        return "bookmark/public-list";
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

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Bookmark> edit(@PathVariable Long id) {
        String principalUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        Bookmark bookmark = bookmarkService.findById(id);
        ResponseEntity<Bookmark> response = new ResponseEntity<Bookmark>(bookmark, HttpStatus.OK);

        if (!bookmark.getUser().getUserName().equals(principalUserName)) {
            if (!bookmark.getPrivateBookmark()) {
                //Criar um novo bookmark a a partir de um bookmark de outro usuário.
                Bookmark bookmarkCopy = new Bookmark(bookmark.getUrl());
                bookmarkCopy.setTitle(bookmark.getTitle());
                bookmarkCopy.setTags(bookmark.getTags());
                response = new ResponseEntity<Bookmark>(bookmarkCopy, HttpStatus.OK);
            } else {
                response = new ResponseEntity<Bookmark>(HttpStatus.NOT_FOUND);
            }

        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> destroy(@PathVariable Long id) {
        String principalUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseEntity<String> response = null;

        Bookmark bookmark = bookmarkService.findById(id);

        if (!bookmark.getUser().getUserName().equals(principalUserName)) {
            response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        } else {
            bookmarkService.remove(bookmark);
            response = new ResponseEntity<String>(HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String getComments() {
        return "bookmark/comments";
    }

    @RequestMapping(value = "/scraping", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Bookmark> bookmarkPreview(@RequestParam String url) {
        Bookmark bookmark = WebScraper.scrapingHtml(url);

        //recommendation
        bookmark.setTags(searchService.getTagsSuggest(url));
        return new ResponseEntity<Bookmark>(bookmark, HttpStatus.OK);
    }

    private User loadCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(userName);
        return user;
    }

    private Map<String, Object> configurePagination(Page<Bookmark> pageResult) {
        //pagination
        int current = pageResult.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, pageResult.getTotalPages());

        Map<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("bookmarkList", pageResult);
        modelMap.put("beginIndex", begin);
        modelMap.put("endIndex", end);
        modelMap.put("currentIndex", current);

        return modelMap;
    }
}
