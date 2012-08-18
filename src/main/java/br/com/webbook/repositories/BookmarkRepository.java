/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author maykoone
 */
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, JpaSpecificationExecutor<Bookmark> {

    @Override
    List<Bookmark> findAll();

    @Override
    Page<Bookmark> findAll(Pageable pageable);

    List<Bookmark> findByUser(User user);

//    Page<Bookmark> findByUser(String userName, Pageable pageable);
}
