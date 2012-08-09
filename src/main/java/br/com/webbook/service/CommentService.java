/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import br.com.webbook.domain.Comment;
import java.util.List;

/**
 *
 * @author maykoone
 */
public interface CommentService {

    Comment save(Comment comment);

    void remove(Comment comment);

    Comment findById(Long id);

    List<Comment> list();
}
