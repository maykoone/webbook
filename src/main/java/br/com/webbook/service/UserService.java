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

    User findByUserName(String userName);

    List<User> list();

    User editProfile(User user);

    boolean changePassword(String userName, String oldPassword, String newPassord);

    boolean follow(User follower, User followed);

    boolean unfollow(User follower, User unfollowed);

    boolean isUniqueUserName(String userName);

    boolean isUniqueEmail(String email);
    
    boolean validUnique(User user);
}
