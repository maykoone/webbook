/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.Comment;
import br.com.webbook.repositories.CommentRepository;
import br.com.webbook.repositories.query.CommentSpecifications;
import br.com.webbook.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maykoone
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void remove(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public List<Comment> list() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> listByBookmark(Bookmark bookmark) {
        return commentRepository.findAll(CommentSpecifications.commentsByBookmark(bookmark));
    }
}
