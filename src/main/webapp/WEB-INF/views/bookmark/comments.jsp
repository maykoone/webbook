<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>

<div id="user-info">
    <div id="profile-summary">
        <wb:gravatar email="${userInstance.email}" />
    </div>
    <div id="comment-form"></div>
</div>
<div id="bookmark-summary">
    ${bookmark.title}
</div>    
<div id="comments-list">
    <c:forEach var="comment" items="${comments}">
        <div>
            <wb:gravatar email="${comment.user.email}" />
            <div id="comment-text">
                <p>${comment.text}</p>
            </div>
        </div> 
    </c:forEach>
</div>