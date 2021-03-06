/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.domain;

import br.com.webbook.search.IndexPublicBookmarkInterceptor;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.impl.BuiltinIterableBridge;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author maykoone
 */
@Entity
@Table(name = "wb_bookmark")
@Indexed(interceptor = IndexPublicBookmarkInterceptor.class)
public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "wb_bookmark_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "wb_bookmark_seq", sequenceName = "wb_bookmark_seq", allocationSize = 1)
    private Long id;
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String title;
    @URL(message = "entre com uma URL válida")
    @NotNull(message = "url é um campo obrigatório")
    @NotBlank(message = "url é um campo obrigatório")
    private String url;
    private String hashUrl;
    @Size(max = 140, message = "A descrição pode ter no máximo 140 caracteres")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String description;
    private Boolean privateBookmark;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wb_bookmark_tags", joinColumns = {
        @JoinColumn(name = "bookmark_id")})
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @FieldBridge(impl = BuiltinIterableBridge.class)
    private Set<String> tags = new HashSet<String>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_account")
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookmark", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Comment> comments;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public Bookmark(String url) {
        this.url = url;
    }

    public Bookmark(String title, String url, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public Bookmark() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getter, setter and other methods">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getPrivateBookmark() {
        return privateBookmark != null ? privateBookmark : false;
    }

    public void setPrivateBookmark(Boolean privateBookmark) {
        this.privateBookmark = privateBookmark != null ? privateBookmark : false;
    }

    public String getHashUrl() {
        return hashUrl;
    }

    public void setHashUrl(String hashUrl) {
        this.hashUrl = hashUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Bookmark other = (Bookmark) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bookmark{" + "url=" + url + '}';
    }
    //</editor-fold>
}
