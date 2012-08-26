/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories.query;

import br.com.webbook.domain.Filter;
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
public class FilterSpecifications {
    
    public static Specification<Filter> filtersByUser(User user) {
        return new Specification<Filter>() {
            private User userSearch;

            public Specification<Filter> setUserSearch(User userSearch) {
                this.userSearch = userSearch;
                return this;
            }

            @Override
            public Predicate toPredicate(Root<Filter> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.join("user").get("userName"), userSearch.getUserName());
            }
        }.setUserSearch(user);
    }
}
