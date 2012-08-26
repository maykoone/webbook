/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Filter;
import br.com.webbook.domain.User;
import br.com.webbook.repositories.FilterRepository;
import br.com.webbook.repositories.query.FilterSpecifications;
import br.com.webbook.service.FilterService;
import java.util.List;
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
public class FilterServiceImpl implements FilterService {

    @Autowired
    private FilterRepository filterRepository;

    @Override
    @Transactional
    public Filter save(Filter filter) {
        return filterRepository.save(filter);
    }

    @Override
    @Transactional
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

    @Override
    public Page<Filter> findByUser(User user, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize);
        Page<Filter> pageResult = filterRepository.findAll(FilterSpecifications.filtersByUser(user), request);

        return pageResult;
    }

    @Override
    public long countByUser(User user) {
        return filterRepository.count(FilterSpecifications.filtersByUser(user));
    }
}
