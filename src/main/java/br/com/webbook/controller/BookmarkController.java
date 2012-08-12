/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("bookmark/list", "bookmarkList", bookmarkService.list());
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("bookmark/create", "bookmark", new Bookmark());
    }

    @RequestMapping( method = RequestMethod.POST)
    public String save(Bookmark bookmark) {
        bookmarkService.save(bookmark);
        return "redirect:/bookmarks";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable Long id) {
        Bookmark bookmark = bookmarkService.findById(id);
        return new ModelAndView("bookmark/show", "bookmark", bookmark);
    }

    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        Bookmark bookmark = bookmarkService.findById(id);
        return new ModelAndView("bookmark/edit", "bookmark", bookmark);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String update(Bookmark bookmark) {
        bookmarkService.save(bookmark);
        return "redirect:/bookmarks";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        bookmarkService.remove(bookmarkService.findById(id));
        return "redirect:/bookmark";
    }
}
