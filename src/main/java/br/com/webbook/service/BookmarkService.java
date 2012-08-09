/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import br.com.webbook.domain.Bookmark;
import java.util.List;

/**
 *
 * @author maykoone
 */
public interface BookmarkService {

    Bookmark save(Bookmark bookmark);

    void remove(Bookmark bookmark);

    Bookmark findById(Long id);

    List<Bookmark> list();
}
