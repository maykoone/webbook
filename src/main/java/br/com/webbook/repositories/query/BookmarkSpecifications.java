/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories.query;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author maykoone
 */
public class BookmarkSpecifications {

    public static Specification<Bookmark> bookmarksByUser(User user) {
        return new Specification<Bookmark>() {
            private User userSearch;

            public Specification<Bookmark> setUserSearch(User userSearch) {
                this.userSearch = userSearch;
                return this;
            }

            @Override
            public Predicate toPredicate(Root<Bookmark> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.join("user").get("userName"), userSearch.getUserName());
            }
        }.setUserSearch(user);
    }

    public static Specification<Bookmark> publicBookmarksByUser(User user) {
        return new Specification<Bookmark>() {
            private User userSearch;

            public Specification<Bookmark> setUserSearch(User userSearch) {
                this.userSearch = userSearch;
                return this;
            }

            @Override
            public Predicate toPredicate(Root<Bookmark> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get("privateBookmark"), false), cb.equal(root.join("user").get("userName"), userSearch.getUserName()));
            }
        }.setUserSearch(user);
    }
}
