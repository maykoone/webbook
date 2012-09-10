/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.search;

import java.util.Collection;
import java.util.Iterator;
import org.hibernate.search.bridge.StringBridge;

/**
 *
 * @author maykoone
 */
public class TagsToStringBrigde implements StringBridge {

    @Override
    public String objectToString(Object value) {
        if (value != null) {
            StringBuilder buf = new StringBuilder();

            Collection<?> col = (Collection<?>) value;
            Iterator<?> it = col.iterator();
            while (it.hasNext()) {
                String next = it.next().toString();
                buf.append(next);
                if (it.hasNext()) {
                    buf.append(", ");
                }
            }
            return buf.toString();
        }
        return null;
    }
}
