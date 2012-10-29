/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.service.SearchService;
import br.com.webbook.utils.MD5Util;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maykoone
 */
@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<String, Long> tagsByUser(String userName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();

        Root<Bookmark> root = query.from(Bookmark.class);
        //joins
        SetJoin<Bookmark, String> tagsJoin = root.joinSet("tags");
        Join<Bookmark, User> userJoin = root.join("user");


        query.select(cb.tuple(tagsJoin.alias("tag"), cb.count(tagsJoin).alias("count_tags")));
        query.where(cb.equal(userJoin.get("userName"), userName));
        query.groupBy(tagsJoin);

        List<Tuple> result = entityManager.createQuery(query).getResultList();

        Map<String, Long> tagRanking = new TreeMap<String, Long>();

        for (Tuple t : result) {
            tagRanking.put((String) t.get("tag"), (Long) t.get("count_tags"));
        }

        return tagRanking;
    }
    

    @Override
    public Set<String> getTagsSuggest(String url) {
        String hashUrl = MD5Util.md5Hex(url);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();

        Root<Bookmark> root = query.from(Bookmark.class);
        //joins
        SetJoin<Bookmark, String> tagsJoin = root.joinSet("tags");

        query.select(cb.tuple(tagsJoin.alias("tag"), cb.count(tagsJoin).alias("count_tags")));
        query.where(cb.equal(root.get("hashUrl"), hashUrl));
        query.groupBy(tagsJoin);
        query.orderBy(cb.desc(cb.count(tagsJoin)));

        //top five tags
        List<Tuple> result = entityManager.createQuery(query).setMaxResults(5).getResultList();

        Set<String> topTags = new HashSet<String>();

        for (Tuple t : result) {
            topTags.add((String) t.get("tag"));
        }

        return topTags;
    }

    @Override
    public List<User> searchUsers(String querySearch) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();

        //query nativa do apache lucene
        org.apache.lucene.search.Query query = qb.keyword().onFields("userName").matching(querySearch).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);

        //@TODO: paginação
        return jpaQuery.getResultList();

    }

    @Override
    public List<Bookmark> searchBookmarks(String querySearch) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Bookmark.class).get();

        //query nativa do apache lucene
        org.apache.lucene.search.Query query = qb.keyword().onFields("title", "description", "tags").matching(querySearch).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, Bookmark.class);

        //@TODO: paginação
        return jpaQuery.getResultList();
    }
}
