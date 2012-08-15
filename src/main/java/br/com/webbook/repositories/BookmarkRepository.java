/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories;

import br.com.webbook.domain.Bookmark;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maykoone
 */
//@Transactional(readOnly = true)
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {

    @Override
    List<Bookmark> findAll();
    
    Page<Bookmark> findAll(Pageable pageable);
}
