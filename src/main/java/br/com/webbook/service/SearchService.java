/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.User;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author maykoone
 */
public interface SearchService {

    Map<String, Long> tagsByUser(String userName);

    Set<String> getTagsSuggest(String url);

    List<User> searchUsers(String querySearch);

    List<Bookmark> searchBookmarks(String querySearch);
}
