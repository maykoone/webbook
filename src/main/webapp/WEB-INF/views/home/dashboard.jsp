<%-- 
    Document   : dashboard
    Created on : 28/10/2012, 15:09:49
    Author     : maykoone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            #tag-cloud {
                margin: 25px auto;
                font-size: 75%;
                text-align: center
            }
            #tag-cloud a {
                margin-left: 2px;
                margin-right: 2px;
                border: none;
                display: inline-block;
            }

        </style>
    </head>
    <body>
        <div class="grid_12">
            <section class="wb-box-with-shadow popular-content">
                <h3>Tags</h3>
                <p class="muted">Veja as tags mais populares no Webbook e descubra novos favoritos</p>
                <hr>
                <div id="tag-cloud" style="display: none">
                    <c:forEach items="${countTags}" var="tag">
                        <a href="${pageContext.request.contextPath}/bookmarks/tag/${tag.key.replaceAll("[^a-zA-Z 0-9,ã,á,à,â,ê,í,ú,ù,õ,é,ü]+","-").replaceAll("\\s", "")}" title="${tag.value} bookmarks" rel="${tag.value}">${tag.key}</a>
                    </c:forEach>
                </div>
            </section>
        </div>
        <div class="grid_12">
            <section class="wb-box-with-shadow popular-content">
                <h3>Favoritos</h3>
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Links Populares</a></li>
                        <li><a href="#tab2" data-toggle="tab">Recentes</a></li>
                        <li><a href="#tab3" data-toggle="tab">Favoritos de quem você segue</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <c:forEach items="${popularBookmarks}" var="bookmark">
                                <div class="search-item">
                                    <div class="user-result">
                                        <h4><a href="">${bookmark.url}</a></h4>
                                    </div>
                                </div>
                                <hr />
                            </c:forEach>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <c:forEach items="${recentBookmarks}" var="bookmark">
                                <div class="search-item">
                                    <div class="user-result">
                                        <div class="avatar img-polaroid">
                                            <wb:gravatar email="${bookmark.user.email}" />
                                        </div>
                                        <h4>${bookmark.title}&nbsp;<small><fmt:formatDate value="${bookmark.creationDate}" pattern="d MMM, yyyy"/></small></h4>
                                        <p><a href=""><small>${bookmark.url}</small></a></p>
                                        <div class="details">
                                            <a href="${pageContext.request.contextPath}/${bookmark.user.userName}">@${bookmark.user.userName}</a>
                                        </div>
                                    </div>
                                </div>
                                <hr />
                            </c:forEach>
                        </div>
                        <div class="tab-pane" id="tab3">
                            <c:forEach items="${followingsBookmarks}" var="bookmark">
                                <div class="search-item">
                                    <div class="user-result">
                                        <div class="avatar img-polaroid">
                                            <wb:gravatar email="${bookmark.user.email}" />
                                        </div>
                                        <h4>${bookmark.title}&nbsp;<small><fmt:formatDate value="${bookmark.creationDate}" pattern="d MMM, yyyy"/></small></h4>
                                        <p><a href=""><small>${bookmark.url}</small></a></p>
                                        <div class="details">
                                            <a href="${pageContext.request.contextPath}/${bookmark.user.userName}">@${bookmark.user.userName}</a>
                                        </div>
                                    </div>
                                </div>
                                <hr /> 
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <script type="text/javascript">
            $(document).ready(function() { 
                $("#tag-cloud a").tagcloud({ 
                    size: { 
                        start: 14, 
                        end: 25, 
                        unit: 'pt' 
                    }, 
                    color: { 
                        start: "#999", 
                        end: "#005580" 
                    } 
                });
                $('div#tag-cloud').fadeIn("slow");
            });
        </script>
    </body>
</html>
