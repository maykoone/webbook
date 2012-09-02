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
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Amigos que seguem você</title>
        <!--<link rel="stylesheet" href="css/tagify-style.css" />-->
        <!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css" />-->
        <!--<link rel="stylesheet" href="resources/css/bootstrap.css" />-->


    </head>
    <c:set var="rootUrl" value="${pageContext.request.contextPath}" />
    <body>
        <div class="grid_12">
            <section id="" class="wb-box-with-shadow popular-content">
                <div class="list-header">
                    <h4 class="wb-left-float">Amigos que você acompanha</h4>
                    <wb:message messageBean="${message}" />
                </div>
                <c:forEach items="${followers}" var="follower">
                    <div class="friendship">
                        <a href="${rootUrl}/users/${follower.follower.userName}" class="avatar img-polaroid">
                            <wb:gravatar email="${follower.follower.email}" />
                        </a>
                        <div class="friendship-info">
                            <h4><a href="${rootUrl}/users/${follower.follower.userName}" class="">@${follower.follower.userName}</a></h4>
                            <p></p>
                        </div>
                        <div class="friendship-control">
                            <ul>
                                <li>
                                    <a href="${rootUrl}/bookmarks/${follower.follower.userName}" >${fn:length(follower.follower.bookmarks)} Favoritos<i class="icon-bookmark"></i></a>
                                </li>
                                <li>
                                    <c:choose>
                                        <c:when test="${userInstance.isFollowing(follower.follower)}">
                                            <span class="label label-info">Acompanhando</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${rootUrl}/users/${follower.follower.userName}/follow" class="btn btn-mini btn-primary">Acompanhar</a>
                                        </c:otherwise>
                                    </c:choose>

                                </li>
                                <li>
                                    <a href="${rootUrl}/users/${follower.follower.userName}/block" class="btn btn-warning btn-mini">Bloquear</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
            </section>
        </div>
    </body>
</html>

