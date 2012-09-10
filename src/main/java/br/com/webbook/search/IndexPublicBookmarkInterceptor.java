/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.search;

import br.com.webbook.domain.Bookmark;
import org.hibernate.search.indexes.interceptor.EntityIndexingInterceptor;
import org.hibernate.search.indexes.interceptor.IndexingOverride;

/**
 *
 * @author maykoone
 */
public class IndexPublicBookmarkInterceptor implements EntityIndexingInterceptor<Bookmark> {

    @Override
    public IndexingOverride onAdd(Bookmark entity) {
        if (!entity.getPrivateBookmark()) {
            return IndexingOverride.APPLY_DEFAULT;
        }
        return IndexingOverride.SKIP;
    }

    @Override
    public IndexingOverride onUpdate(Bookmark t) {
        if (!t.getPrivateBookmark()) {
            return IndexingOverride.UPDATE;
        }
        return IndexingOverride.REMOVE;
    }

    @Override
    public IndexingOverride onDelete(Bookmark t) {
        return IndexingOverride.APPLY_DEFAULT;
    }

    @Override
    public IndexingOverride onCollectionUpdate(Bookmark t) {
        return onUpdate(t);
    }
}
