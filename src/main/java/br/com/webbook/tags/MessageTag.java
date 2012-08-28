/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author maykoone
 */
public class MessageTag extends SimpleTagSupport {

    private MessageBean messageBean;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        String cssClassName = "alert";

        try {
            if (messageBean != null) {
                switch (messageBean.getType()) {
                    case INFO:
                        cssClassName += " alert-info";
                        break;
                    case WARNING:
                        cssClassName += " alert-warning";
                        break;
                    case ERROR:
                        cssClassName += " alert-error";
                        break;
                    case SUCESS:
                        cssClassName += " alert-success";
                        break;
                }

                out.println("<div class=\"" + cssClassName + "\">");
                out.println("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>");
                out.println(messageBean);
                out.println("</div>");
            }

        } catch (IOException iOException) {
            throw new JspException("erro ao processar tag");
        }
    }

    public void setMessageBean(Object messageBean) {
        if (messageBean instanceof MessageBean) {
            this.messageBean = (MessageBean) messageBean;
        }
    }
}
