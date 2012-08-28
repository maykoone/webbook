/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.repositories.BookmarkRepository;
import br.com.webbook.repositories.query.BookmarkSpecifications;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.utils.MD5Util;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        //para facilitar as pesquisas sobre a url
        String hashUrl = MD5Util.md5Hex(bookmark.getUrl());
        bookmark.setHashUrl(hashUrl);
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
        Page<Bookmark> pageResult = bookmarkRepository.findAll(BookmarkSpecifications.bookmarksByUser(user), request);

        return pageResult;
    }

    @Override
    public long countByUser(User user) {
        return bookmarkRepository.count(BookmarkSpecifications.bookmarksByUser(user));
    }

    @Override
    public Page<Bookmark> listPublicBookmarksByUser(User user, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize);
        Page<Bookmark> pageResult = bookmarkRepository.findAll(BookmarkSpecifications.publicBookmarksByUser(user), request);

        return pageResult;
    }

    @Override
    public Page<Bookmark> listPublicBookmarksByTags(Set<String> tags, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize);
        Page<Bookmark> pageResult = bookmarkRepository.findDistinctByTagsInAndPrivateBookmark(tags, false, request);

        return pageResult;
    }
}
