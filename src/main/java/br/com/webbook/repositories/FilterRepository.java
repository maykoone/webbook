/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories;

import br.com.webbook.domain.Filter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author maykoone
 */

public interface FilterRepository extends JpaRepository<Filter, Long>, JpaSpecificationExecutor<Filter> {

    @Override
    List<Filter> findAll();
}
