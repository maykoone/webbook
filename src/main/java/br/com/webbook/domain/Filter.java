/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author maykoone
 */
@Entity
@Table(name = "wb_filter")
public class Filter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "wb_filter_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "wb_filter_seq", sequenceName = "wb_filter_seq", allocationSize = 1)
    private Long id;
    @NotBlank(message = "O campo título não pode ser vazio")
    @Size(max = 50, message = "O tamanho máximo do título é de 50 caracteres")
    private String title;
    @Size(max = 140, message = "O campo descrição deve ter no máximo 140 caracteres")
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wb_filter_tags", joinColumns = {
        @JoinColumn(name = "filter_id")})
    private Set<String> tags;
    @ManyToOne
    @JoinColumn(name = "user_account")
    @JsonIgnore
    private User user;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Filter other = (Filter) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Filter{" + "id=" + id + '}';
    }
}
