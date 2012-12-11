package br.com.webbook.tags;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Funções para serem utilizadas nas páginas JSP;
 * @author maykoone
 */
public class Functions {
    
    /**
     * codifica uma url no padrão UTF-8
     * @param value
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String urlEncode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }
}
