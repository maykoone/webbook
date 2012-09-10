/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.support.scraping;

import br.com.webbook.domain.Bookmark;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author maykoone
 */
public class WebScraper {

    public static Bookmark scrapingHtml(String url) {
        if (url == null || url.equals("")) {
            return null;
        }

        try {
            Document doc = Jsoup.connect(url).get();

            String title = doc.title();
            Elements descriptionQuery = doc.select("meta[name=description]");
            String desc = "";
            if (descriptionQuery != null && !descriptionQuery.isEmpty()) {
                desc = descriptionQuery.first().attr("content");
            }
//            Elements tagsQuery = doc.select("meta[name=keywords]");
//            String tags = "";
//            if (tagsQuery != null && !tagsQuery.isEmpty()) {
//                tags = tagsQuery.first().attr("content");
//            }

            return new Bookmark(title, url, desc);

        } catch (IOException e) {
            Logger.getLogger(WebScraper.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } catch (Exception e) {
            Logger.getLogger(WebScraper.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
