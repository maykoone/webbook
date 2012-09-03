/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service;

import java.util.Map;

/**
 *
 * @author maykoone
 */
public interface SearchService {
     Map<String, Long> tagRankingByUser(String userName);
    
}
