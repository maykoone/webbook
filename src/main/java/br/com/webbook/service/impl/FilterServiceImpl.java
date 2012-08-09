/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Filter;
import br.com.webbook.repositories.FilterRepository;
import br.com.webbook.service.FilterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maykoone
 */
@Service
@Transactional
public class FilterServiceImpl implements FilterService {

    @Autowired
    private FilterRepository filterRepository;

    @Override
    public Filter save(Filter filter) {
        return filterRepository.save(filter);
    }

    @Override
    public void remove(Filter filter) {
        filterRepository.delete(filter);
    }

    @Override
    public Filter findById(Long id) {
        return filterRepository.findOne(id);
    }

    @Override
    public List<Filter> list() {
        return filterRepository.findAll();
    }
}
