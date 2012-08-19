/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.tags;

import de.bripkens.gravatar.DefaultImage;
import de.bripkens.gravatar.Gravatar;
import de.bripkens.gravatar.Rating;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author maykoone
 */
public class Gravatar4JavaTag extends SimpleTagSupport {

    private String email;
    private Integer size;
    private String defaultImage;
    private Boolean forceDefault;
    private String cssClass;
    private String id;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            String gravatarImageURL = new Gravatar()
                    .setSize(size == null ? 44 : size)
                    .setHttps(true)
                    .setRating(Rating.GENERAL_AUDIENCE)
                    .setStandardDefaultImage(DefaultImage.IDENTICON)
                    .getUrl(email);// TODO: insert code to write html before writing the body content.

            out.println("<img src=\"" + gravatarImageURL + "\" />");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Gravatar4JavaTag tag", ex);
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public void setForceDefault(Boolean forceDefault) {
        this.forceDefault = forceDefault;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
