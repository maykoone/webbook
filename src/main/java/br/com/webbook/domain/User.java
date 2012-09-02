/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.domain;

import br.com.webbook.validation.ProfileChecks;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author maykoone
 */
@Entity
@Table(name = "wb_user_account", uniqueConstraints = {
    @UniqueConstraint(name = "unique_username", columnNames = "username")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "wb_user_account_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "wb_user_account_seq", sequenceName = "wb_user_account_seq", allocationSize = 1)
    private Long id;
    @Size(min = 3, max = 30, message = "O nome deve ter no mínimo 3 e no máximo 30 caracteres.", groups = {ProfileChecks.class})
    private String name;
    @Size(min = 3, max = 30, message = "O sobrenome deve ter no mínimo 3 e no máximo 30 caracteres.", groups = {ProfileChecks.class})
    private String lastName;
    @NotNull(message = "A senha é obrigatória", groups = {Default.class})
    @NotBlank(message = "A senha é obrigatória", groups = {Default.class})
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres.", groups = {Default.class})
    @JsonIgnore
    private String password;
    @NotNull(message = "O email é obrigatório", groups = {Default.class, ProfileChecks.class})
    @NotBlank(message = "O email é obrigatória", groups = {Default.class, ProfileChecks.class})
    @Email(message = "digite um email válido", groups = {Default.class, ProfileChecks.class})
    private String email;
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter no mínimo 3 e no máximo 20 caracteres", groups = {Default.class, ProfileChecks.class})
    @NotBlank(message = "O nome de usuário é obrigatório", groups = {Default.class, ProfileChecks.class})
    @NotNull(message = "O nome de usuário é obrigatório", groups = {Default.class, ProfileChecks.class})
    private String userName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<Bookmark> bookmarks;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Filter> filters;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "followed")
    private Set<Friendship> followers;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "follower")
    private Set<Friendship> followings;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public User() {
    }

    public User(String password, String email, String userName) {
        this.password = password;
        this.email = email;
        this.userName = userName;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getters, setters and other methods">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Set<Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Filter> filters) {
        this.filters = filters;
    }

    public Set<Friendship> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Friendship> followers) {
        this.followers = followers;
    }

    public Set<Friendship> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<Friendship> followings) {
        this.followings = followings;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.userName == null) ? (other.userName != null) : !this.userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return userName;
    }
    //</editor-fold>
    
    /**
     * método para verificar se um dado usuário está sendo seguido pelo
     * usuário atual. (this)
     * @param user
     * @return 
     */
    public boolean isFollowing(User user) {
        if (this.getFollowings() == null) {
            return false;
        }

        for (Friendship f : getFollowings()) {
            if (f.getFollowed().equals(user)) {
                return true;
            }
        }
        return false;
    }

    
}
