/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.domain;

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
import org.hibernate.validator.constraints.Email;

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
    @Size(min=3, max=30, message="O nome deve ter no mínimo 3 e no máximo 30 caracteres.")
    private String name;
    @Size(min=3, max=30, message="O sobrenome deve ter no mínimo 3 e no máximo 30 caracteres.")
    private String lastName;
    @NotNull(message="A senha é obrigatória")
    @Size(min=3, max=20, message="A senha deve ter no mínimo 3 e no máximo 20 caracteres.")
    private String password;
    @NotNull(message="O email é obrigatório")
    @Email
    private String email;
    @Size(min=3, max=20, message="O nome de usuário deve ter no mínimo 3 e no máximo 20 caracteres")
    private String userName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Bookmark> bookmarks;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Filter> filters;

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
}
