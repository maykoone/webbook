/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import br.com.webbook.domain.User;
import java.util.List;

/**
 *
 * @author maykoone
 */
public interface UserService {

    User save(User user);

    void remove(User user);

    User findById(Long id);

    List<User> list();
}
