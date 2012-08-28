/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author maykoone
 */
@Entity
@Table(name = "friendship", uniqueConstraints = {
    @UniqueConstraint(name = "unique_friendship", columnNames = {"follower", "followed"})})
public class Friendship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "friendship_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "friendship_seq", sequenceName = "friendship_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn( name = "follower")
    private User follower;
    @ManyToOne
    @JoinColumn( name = "followed")
    private User followed;

    public Friendship() {
    }

    public Friendship(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friendship)) {
            return false;
        }
        Friendship other = (Friendship) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.webbook.domain.Friendship[ id=" + id + " ]";
    }
}
