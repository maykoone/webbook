<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<li class="comment-item">
    <div class="comment-content">
        <a href="" class="avatar">
            <wb:gravatar email="${comment.user.email}" />
        </a>   
        <div class="comment-text">
            <h6>Comentário por <a href="/bookmarks/${comment.user.userName}">${comment.user.userName}</a> em <fmt:formatDate value="${comment.creationDate}" pattern="d MMM, yyyy hh:mm"/></h6>
            <p>${comment.text}</p>
        </div>
    </div>
</li>