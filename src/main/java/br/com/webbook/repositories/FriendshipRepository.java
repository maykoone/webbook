/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories;

import br.com.webbook.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author maykoone
 */
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    
}
