<%-- 
    Document   : dashboard
    Created on : 28/10/2012, 15:09:49
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="grid_12">
            <section class="wb-box-with-shadow popular-content">
                <h3>Tags</h3>
                <p class="muted">Veja as tags mais populares no Webbook e descubra novos favoritos</p>
                <hr>
                <div class="tag-cloud"></div>
            </section>
        </div>
        <div class="grid_12">
            <section class="wb-box-with-shadow popular-content">
                <h3>Favoritos</h3>
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Populares</a></li>
                        <li><a href="#tab2" data-toggle="tab">Recentes</a></li>
                        <li><a href="#tab3" data-toggle="tab">Favoritos de quem você segue</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <p>I'm in Section 1.</p>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <p>Howdy, I'm in Section 2.</p>
                        </div>
                        <div class="tab-pane" id="tab3">
                            <c:forEach items="${followingsBookmarks}" var="bookmark">
                                <p>${bookmark.url}</p>
                                <p>${bookmark.user.userName}</p>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
