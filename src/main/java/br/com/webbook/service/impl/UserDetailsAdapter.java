/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.User;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author maykoone
 */
public class UserDetailsAdapter extends org.springframework.security.core.userdetails.User {

    private final Long id;

    public UserDetailsAdapter(User user) {
        super(user.getUserName(), user.getPassword(), true, true, true, true, toAuthorities());
        this.id = user.getId();
    }

    private static Set<GrantedAuthority> toAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));

        return authorities;
    }

    public Long getId() {
        return id;
    }
}
