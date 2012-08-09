/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories;

import br.com.webbook.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maykoone
 */
@Transactional(readOnly=true)
public interface UserRepository extends CrudRepository<User, Long> {
    
    @Override
    List<User> findAll();
}
