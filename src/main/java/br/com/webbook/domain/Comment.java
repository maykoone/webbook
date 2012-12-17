/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

/**
 *
 * @author maykoone
 */
@Entity
@Table(name = "wb_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "wb_comment_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "wb_comment_seq", sequenceName = "wb_comment_seq", allocationSize = 1)
    private Long id;
    @Size(max = 140, message = "O comentário pode ter no máximo 140 caracteres")
    private String text;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;
    @ManyToOne
//    @JsonIgnore
    private Bookmark bookmark;
    @ManyToOne
    @JoinColumn(name = "user_account")
//    @JsonIgnore()
    private User user;

    //<editor-fold defaultstate="collapsed" desc="getters, setters and other methods">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "text=" + text + '}';
    }
    //</editor-fold>
}
