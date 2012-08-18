/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author maykoone
 */
public interface BookmarkService {

    Bookmark save(Bookmark bookmark);

    void remove(Bookmark bookmark);

    Bookmark findById(Long id);

    List<Bookmark> list();

    Page<Bookmark> list(Integer pageNumber, Integer pageSize);

    Page<Bookmark> listByUser(User user, Integer pageNumber, Integer pageSize);
}
