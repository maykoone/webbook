/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import br.com.webbook.service.SearchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
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
    public Map<String, Long> tagRankingByUser(String userName) {
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

        Map<String, Long> tagRanking = new HashMap<String, Long>();

        for (Tuple t : result) {
            tagRanking.put((String) t.get("tag"), (Long) t.get("count_tags"));
        }

        return tagRanking;
    }
}
