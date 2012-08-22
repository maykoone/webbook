/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.repositories.query;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.Comment;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author maykoone
 */
public class CommentSpecifications {

    /**
     * Consulta para buscar todos os comments de um bookmark, irá trazer todos
     * os comentários para bookmarks com a mesma url.
     * @param bookmark
     * @return 
     */
    public static Specification<Comment> commentsByBookmark(Bookmark bookmark) {
        return new Specification<Comment>() {
            private Bookmark bookmarkSeach;

            public Specification<Comment> setBookmarkSearch(Bookmark bookmarkSearch) {
                this.bookmarkSeach = bookmarkSearch;
                return this;
            }

            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join<Comment, Bookmark> joinBookmark = root.join("bookmark");
                return cb.and(cb.equal(joinBookmark.get("hashUrl"), bookmarkSeach.getHashUrl()),
                        cb.equal(joinBookmark.get("privateBookmark"), false));
            }
        }.setBookmarkSearch(bookmark);
    }
}
