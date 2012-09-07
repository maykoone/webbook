/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.support.scraping;

/**
 *
 * @author maykoone
 */
public class BookmarkScraping {

    private String url;
    private String description;
    //@TODO: change to set of strings
    private String keywords;
    private String title;

    public BookmarkScraping() {
    }

    public BookmarkScraping(String url, String description, String keywords, String title) {
        this.url = url;
        this.description = description;
        this.keywords = keywords;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
