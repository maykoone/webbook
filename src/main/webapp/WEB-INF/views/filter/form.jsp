<%-- 
    Document   : create
    Created on : 25/08/2012, 15:50:22
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
            <section class="user-details wb-box-with-shadow popular-content">
                <a href="" class="avatar">
                    <!-- <img src="http://springpad.com/api/users/maykoone/photo?w=80&h=80" alt="avatar" /> -->
                    <wb:gravatar email="${userInstance.email}" />
                </a>
                <div class="user-info">
                    <a hreaf="" class="wb-font-big">
                        <strong>@<sec:authentication property="principal.username" /></strong>
                    </a>&nbsp;<span class="wb-font-big">[${userInstance.name}]</span>
                    <ul class="user-stats">
                        <li class="wb-font-small"><a href="" rel="tooltip" title="first tooltip">${bookmarkCount} Bookmarks</a></li>
                        <li class="wb-font-small"><a href="users/following">10 Amigos que você acompanha</a></li>
                        <li class="wb-font-small"><a href="users/followers">10 Amigos que te acompanham</a></li>
                        <li class="wb-font-small"><a href="users/filters">${filterCount} Filtros</a></li>
                        <li class="wb-font-small"><strong><a href="users/account/profile" class="btn">Edite seu Perfil</a></strong></li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="clear"></div>
        <div class="grid_8" >
            <div class="wb-box-with-shadow">
                <h4>Filtros</h4>
                <c:set var="formMethod" value="post" />
                <c:if test="${filterInstance.id ne null}">
                    <c:set var="formMethod" value="put"/>
                </c:if>
                <form:form action="${pageContext.request.contextPath}/filters" commandName="filterInstance" method="${formMethod}">
                    <form:hidden path="id" />
                    <div class="field-block">
                        <div class="field-title">
                            <label>Título</label>
                        </div>
                        <div class="field-input">
                            <form:errors path="title" />
                            <input class="" type="text" name="title" value="${filterInstance.title}" />
                        </div>
                    </div>
                    <div class="field-block">
                        <div class="field-title">
                            <form:errors path="description" />
                            <label>Descrição</label>
                        </div>
                        <div class="field-input">
                            <textarea rows="3" cols="4" name="description">${filterInstance.description}</textarea>
                        </div>
                    </div>
                    <div class="field-block">
                        <div class="field-title">
                            <label>Tags</label>
                        </div>
                        <div class="field-input">
                            <input class=""type="text" name="tags" value="${filterInstance.tags}" />
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-primary" >Salvar</button>
                            <button type="reset" class="btn" >Cancelar</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="grid_4">
            <div class="wb-box-with-shadow popular-content" id="comments">
                <h4>Lorem Ipsum</h4>
                <p class="wb-font-medium" >"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."</p>
            </div>
        </div>
        
    </body>
</html>
