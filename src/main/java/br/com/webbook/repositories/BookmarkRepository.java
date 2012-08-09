/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories;

import br.com.webbook.domain.Bookmark;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maykoone
 */
//@Transactional(readOnly = true)
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {

    @Override
    List<Bookmark> findAll();
}
