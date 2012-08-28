/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.tags;

/**
 *
 * @author maykoone
 */
public class MessageBean {

    private final String message;
    private final MessageBean.TYPE type;

    public enum TYPE {

        INFO, WARNING, ERROR, SUCESS
    }

    public MessageBean(String message, MessageBean.TYPE type) {
        this.message = message;
        this.type = type;
    }

    public MessageBean(String message) {
        this.message = message;
        this.type = MessageBean.TYPE.INFO;
    }

    public String getMessage() {
        return message;
    }

    public TYPE getType() {
        return type;
    }

    @Override
    public String toString() {
        return message;
    }
}
