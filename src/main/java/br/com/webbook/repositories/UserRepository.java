/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories;

import br.com.webbook.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maykoone
 */
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    User findByUserName(String userName);

    User findByEmail(String email);

    List<User> findByName(String name);
}
