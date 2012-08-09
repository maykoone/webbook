/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import br.com.webbook.domain.Filter;
import java.util.List;

/**
 *
 * @author maykoone
 */
public interface FilterService {

    Filter save(Filter filter);

    void remove(Filter filter);

    Filter findById(Long id);

    List<Filter> list();
}
