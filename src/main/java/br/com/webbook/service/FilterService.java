/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import br.com.webbook.domain.Filter;
import br.com.webbook.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author maykoone
 */
public interface FilterService {

    Filter save(Filter filter);

    void remove(Filter filter);

    Filter findById(Long id);

    List<Filter> list();
    
    Page<Filter> findByUser(User user, Integer pageNumber, Integer pageSize);
    
    long countByUser(User user);
}
