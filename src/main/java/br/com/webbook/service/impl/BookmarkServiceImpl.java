/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.repositories.BookmarkRepository;
import br.com.webbook.service.BookmarkService;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maykoone
 */
@Service
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public void setBookmarkRepository(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    @Transactional
    public Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Override
    @Transactional
    public void remove(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public Bookmark findById(Long id) {
        return bookmarkRepository.findOne(id);
    }

    @Override
    public List<Bookmark> list() {
        return bookmarkRepository.findAll();
    }

    @Override
    public Page<Bookmark> list(Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize);
        return bookmarkRepository.findAll(request);
    }

    @Override
    public Page<Bookmark> listByUser(User user, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize);
        Page<Bookmark> pageResult = bookmarkRepository.findAll(new Specification<Bookmark>() {
            private User userSearch;

            public Specification<Bookmark> setUserSearch(User userSearch) {
                this.userSearch = userSearch;
                return this;
            }

            @Override
            public Predicate toPredicate(Root<Bookmark> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.join("user").get("userName"), userSearch.getUserName());
            }
        }.setUserSearch(user), request);

        return pageResult;
    }
}
