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
        <title>Amigos que você segue</title>


    </head>
    <body>
        <div class="grid_12">
            <section id="" class="wb-box-with-shadow popular-content">
                <div class="list-header">
                    <wb:message messageBean="${message}" />
                    <c:choose>
                        <c:when test="${userSearch != null}">
                            <h4>Amigos que ${userSearch} segue</h4>
                        </c:when>
                        <c:otherwise>
                            <h4>Amigos que você segue</h4>
                        </c:otherwise>
                    </c:choose>

                </div>
                <c:forEach items="${followings}" var="following">
                    <div class="friendship">
                        <a href="/users/${following.followed.userName}" class="avatar img-polaroid">
                            <wb:gravatar email="${following.followed.email}" />
                        </a>
                        <div class="friendship-info">
                            <h4><a href="/users/${following.followed.userName}" class="">@${following.followed.userName}</a></h4>
                            <a href="" >${following.followed.name}</a>
                        </div>
                        <div class="friendship-control">
                            <ul>
                                <li>
                                    <a href="/bookmarks/${following.followed.userName}" ><i class="icon-bookmark"></i> Favoritos (${fn:length(following.followed.bookmarks)})</a>
                                </li>
                                <li>
                                    <c:choose>
                                        <c:when test="${userSearch != null}">
                                            <c:choose>
                                                <c:when test="${userInstance.isFollowing(following.followed)}">
                                                    <span class="label label-info">Seguindo</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${following.followed ne userInstance }">
                                                        <a href="/users/${following.followed.userName}/follow" class="btn btn-mini btn-primary">Seguir</a>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <form:form action="/users/${following.followed.userName}/unfollow" method="delete">
                                                <input type="hidden" name="friendship" value="${following.id}">
                                                <button class="btn btn-mini btn-danger">Parar de seguir</button>
                                            </form:form>
                                        </c:otherwise>
                                    </c:choose>

                                </li>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
            </section>
        </div>
    </body>
</html>

