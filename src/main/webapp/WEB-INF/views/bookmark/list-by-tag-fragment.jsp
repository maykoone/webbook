<%-- 
    Document   : list
    Created on : 11/08/2012, 17:47:37
    Author     : maykoone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/support" %>

<c:forEach items="${bookmarks}" var="bookmark">
    <div class="bookmark-item">
        <a href="" class="bookmark-thumbnail">
            <div class="default-thumb"  <c:if test="${not empty bookmark.iconPath}">style="background-image:url('${bookmark.iconPath}')"</c:if>></div>
            </a>
            <div class="bookmark-info">
                <h3><a href="${bookmark.url}" class="bookmark-title wb-font-medium">${bookmark.title}</a></h3>
            <a href="${bookmark.url}" class="bookmark-url wb-font-small">${bookmark.url}</a>
            <p class="wb-font-small">${bookmark.description}</p>
            <ul class="bookmark-tag-list">
                <c:forEach items="${bookmark.tags}" var="tag">
                    <li><html:tagLink tag="${tag}"/></li>
                </c:forEach>
            </ul>
        </div>
        <div class="bookmark-item-control">
            <ul>
                <li><a href="/bookmarks/${bookmark.id}/edit" class="editMe" onclick="return false;" title="Adicione ao seus favoritos"><i class="icon-bookmark"></i>Salvar</a></li>
                <li>
                    <a href="/ajax/bookmarks/${bookmark.id}/comments" data-toggle="modal" onclick="return false;" class="get-comments">
                        <i class="icon-comment"></i>Comentários
                    </a>
                </li>
                <li><a href="#" class="share-button" data-url="${bookmark.url}" data-title="${bookmark.title}" data-username="${userInstance.userName}"><i class="icon-share"></i>Compartilhar</a></li>
            </ul>
        </div>
    </div>
</c:forEach>



