/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.Friendship;
import br.com.webbook.domain.User;
import br.com.webbook.repositories.BookmarkRepository;
import br.com.webbook.repositories.query.BookmarkSpecifications;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.utils.MD5Util;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    
    @PersistenceContext
    private EntityManager entityManager;

    public void setBookmarkRepository(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    @Transactional
    public Bookmark save(Bookmark bookmark) {
        //para facilitar as pesquisas sobre a url
        String hashUrl = MD5Util.md5Hex(bookmark.getUrl());
        bookmark.setHashUrl(hashUrl);
        bookmark.setCreationDate(new Date());
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
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "creationDate"));
        return bookmarkRepository.findAll(request);
    }

    @Override
    public Page<Bookmark> listByUser(User user, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "creationDate"));
        Page<Bookmark> pageResult = bookmarkRepository.findAll(BookmarkSpecifications.bookmarksByUser(user), request);

        return pageResult;
    }

    @Override
    public long countByUser(User user) {
        return bookmarkRepository.count(BookmarkSpecifications.bookmarksByUser(user));
    }

    @Override
    public Page<Bookmark> listPublicBookmarksByUser(User user, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "creationDate"));
        Page<Bookmark> pageResult = bookmarkRepository.findAll(BookmarkSpecifications.publicBookmarksByUser(user), request);
        return pageResult;
    }

    @Override
    public Page<Bookmark> listPublicBookmarksByTags(Set<String> tags, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "creationDate"));
        Page<Bookmark> pageResult = bookmarkRepository.findDistinctByTagsInAndPrivateBookmark(tags, false, request);
        return pageResult;
    }

    @Override
    public List<Bookmark> listPublicBookmarksFromFollowingsOfUser(User user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bookmark> query = cb.createQuery(Bookmark.class);
        
        Root<Bookmark> from = query.from(Bookmark.class);
        Join<Bookmark, User> joinUsers = from.join("user");
        Join<User, Friendship> joinFriendship = joinUsers.join("followers", JoinType.LEFT);
        
        
        query.select(from).where(cb.equal(joinFriendship.get("follower"), user), cb.isFalse(from.<Boolean>get("privateBookmark")));
        query.orderBy(cb.desc(from.get("creationDate")));
        
        return entityManager.createQuery(query).getResultList();
    }
    
    
}
