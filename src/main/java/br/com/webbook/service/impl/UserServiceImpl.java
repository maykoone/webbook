/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Friendship;
import br.com.webbook.domain.User;
import br.com.webbook.repositories.FriendshipRepository;
import br.com.webbook.repositories.UserRepository;
import br.com.webbook.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maykoone
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    @Transactional
    public User save(User user) {
        userRepository.save(user);
        String encodePassword = passwordEncoder.encodePassword(user.getPassword(), user.getId());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("não existe esse usuário cadastrado");
        }
        return new UserDetailsAdapter(user);
    }

    @Override
    @Transactional
    public User editProfile(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean changePassword(String userName, String oldPassword, String newPassord) {
        User user = findByUserName(userName);
        if (user != null && passwordEncoder.isPasswordValid(user.getPassword(), oldPassword, user.getId())) {
            String encodePassword = passwordEncoder.encodePassword(newPassord, user.getId());
            user.setPassword(encodePassword);
            userRepository.save(user);
            return true;

        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean follow(User follower, User followed) {
        //@TODO: verificar em casos de violação da constraint unique.

        //não pode seguir ele mesmo.
        if (followed.equals(follower)) {
            return false;
        }
        friendshipRepository.save(new Friendship(follower, followed));
        return true;
    }

    @Override
    @Transactional
    public boolean unfollow(User follower, User unfollowed) {
        for (Friendship f : follower.getFollowings()) {
            if (f.getFollowed().equals(unfollowed)) {
                friendshipRepository.delete(f);
                return true;
            }
        }
        return false;
    }
}
