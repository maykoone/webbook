/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.Comment;
import br.com.webbook.domain.User;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.CommentService;
import br.com.webbook.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentController {

    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ajax/bookmarks/{id}/comments")
    public String getComments(@PathVariable Long id, Model model, Principal principal) {

        User user = userService.findByUserName(principal.getName());
        Bookmark bookmark = bookmarkService.findById(id);
        List<Comment> comments = commentService.listByBookmark(bookmark);

        model.addAttribute("bookmark", bookmark);
        model.addAttribute("comments", comments);
        model.addAttribute("userInstance", user);
//        model.addAttribute("commentInstance", new Comment());

        return "comment/comments";

    }

    @RequestMapping(value = "/ajax/comments", method = RequestMethod.POST)
//    @ResponseBody
    public String addComment(Comment comment, Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        comment.setUser(user);
        commentService.save(comment);

        model.addAttribute("comment", comment);
        return "comment/comment-item";
    }
}
