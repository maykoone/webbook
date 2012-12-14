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
<!DOCTYPE html>
<html>
    <head>
        <title>Resultado da pesquisa</title>
        <!--<link rel="stylesheet" href="css/tagify-style.css" />-->
        <!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css" />-->
        <!--<link rel="stylesheet" href="resources/css/bootstrap.css" />-->


    </head>
    <body>
        <div class="grid_12">
            <section id="" class="wb-box-with-shadow popular-content">
                <div class="list-header">
                    <h4 class="wb-left-float">Resultados para pesquisa: ${querySearch}</h4>
                </div>
                <div class="row-fluid">
                    <div class="span6">
                        <h5>Usuários (${fn:length(userResults)})</h5>
                        <wb:message messageBean="${messageUsers}" />
                        <c:forEach items="${userResults}" var="user">
                            <div class="search-item">
                                <div class="user-result">
                                    <div class="avatar img-polaroid">
                                        <wb:gravatar email="${user.email}" />
                                    </div>
                                        <h4><a href="/${user.userName}">${user.userName}</a></h4>
                                    <div class="details">${fn:length(user.bookmarks)} Favoritos | ${fn:length(user.followers)} Seguidores | ${fn:length(user.followings)} Seguindo</div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="span6">
                        <h5>Links (${fn:length(bookmarksResults)})</h5>
                        <wb:message messageBean="${messageBookmarks}" />
                        <c:forEach items="${bookmarksResults}" var="bookmark">
                            <div class="search-item">
                                <p><a href="${bookmark.url}" class="bookmark-title wb-font-medium">${bookmark.title}</a><p>
                                    <a href="${bookmark.url}" class="bookmark-url wb-font-small">${bookmark.url}</a>
                                <p class="wb-font-small">${bookmark.description}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>

