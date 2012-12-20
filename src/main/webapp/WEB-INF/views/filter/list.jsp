<%-- 
    Document   : list
    Created on : 25/08/2012, 17:46:24
    Author     : maykoone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>
<%@ taglib prefix="html" tagdir="/WEB-INF/tags/support" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Seus filtros</title>
    </head>
    <body>
        <div class="grid_12">
            <section class="user-details wb-box-with-shadow popular-content">
                <a href="" class="avatar">
                    <wb:gravatar email="${userInstance.email}" />
                </a>
                <div class="user-info">
                    <a hreaf="" class="wb-font-big">
                        <strong>@<sec:authentication property="principal.username" /></strong>
                    </a>&nbsp;
                    <c:if test="${not empty userInstance.name}">
                        <span class="wb-font-big">[${userInstance.name}]</span>
                    </c:if>
                    <ul class="user-stats">
                        <li class="wb-font-small"><a href="/bookmarks" data-placement="bottom"  rel="tooltip" title="Seus favoritos">${bookmarksCount} Bookmarks</a></li>
                        <li class="wb-font-small"><a href="/users/followings" data-placement="bottom"  rel="tooltip" title="Veja quais amigos você acompanha">${fn:length(userInstance.followings)} Amigos que você acompanha</a></li>
                        <li class="wb-font-small"><a href="/users/followers" data-placement="bottom"  rel="tooltip" title="Veja quais amigos você acompanha">${fn:length(userInstance.followers)} Amigos que te acompanham</a></li>
                        <li class="wb-font-small"><a href="/filters">${filterList.totalElements} Filtros</a></li>
                        <li class="wb-font-small"><strong><a href="/users/account/profile" class="btn btn-mini">Edite seu Perfil</a></strong></li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="clear"></div>
        <div class="grid_12">
            <div class="wb-box-with-shadow popular-content">
                <div class="alert alert-info">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <i class="icon-exclamation-sign"></i> <strong>Uma forma prática de encontrar novos favoritos.</strong> O Filtro te ajuda a encontrar favoritos específicos sem que você precise ficar realizando uma pesquisa para isso. Apenas nomeie seu filtro, adicione as palavras chaves do seu interesse e visualize os favoritos relacionados.
                </div>
                <wb:message messageBean="${message}"/>
                <div class="list-header">
                    <h4 class="wb-left-float">Seus filtros</h4>
                    <a href="/filters/create" class="btn btn-primary btn-mini wb-right-float"><i class="icon-plus icon-white"></i>Adicionar filtro</a>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="grid_12">
            <section class="filter-list" id="filter-list-container">
                <c:forEach var="filter" items="${filterList.content}">
                    <section class="wb-box-with-shadow popular-content filter-item">
                        <div class="filter-thumbnail">
                            <!--<img src="http://placehold.it/300x200" alt="">-->
                            <h3>${filter.title}</h3>
                            <p class="wb-font-medium">${filter.description}</p>
                        </div>

                        <ul class="filter-tag-list">
                            <c:forEach items="${filter.tags}" var="tag">
                                <li><html:tagLink tag="${tag}"/></li>
                            </c:forEach>
                        </ul>
                        <div class="filter-control">
                            <ul>
                                <li><a class="btn btn-mini btn-primary" href="filters/${filter.id}/bookmarks"><i class="icon-eye-open icon-white"></i>Ver</a></li>
                                <li><a class="btn btn-mini" href="filters/${filter.id}/edit"><i class="icon-edit"></i>Editar</a></li>
                                <li><a href="filters/${filter.id}" id="destroy" onclick="return false;" class="btn btn-mini"><i class="icon-remove"></i>Excluir</a></li>
                            </ul>
                        </div>
                    </section>        
                </c:forEach>
            </section>
            <c:url var="firstUrl" value="filters?page=1" />
            <c:url var="lastUrl" value="filters?page=${filterList.totalPages}" />
            <c:url var="prevUrl" value="filters?page=${currentIndex - 1}" />
            <c:url var="nextUrl" value="filters?page=${currentIndex + 1}" />
            <div class="pagination">
                <ul>
                    <c:choose>
                        <c:when test="${currentIndex == 1}">
                            <li class="disabled"><a href="#">&lt;&lt;</a></li>
                            <li class="disabled"><a href="#">&lt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${firstUrl}">&lt;&lt;</a></li>
                            <li><a href="${prevUrl}">&lt;</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                        <c:url var="pageUrl" value="filters?page=${i}" />
                        <c:choose>
                            <c:when test="${i == currentIndex}">
                                <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${currentIndex == filterList.totalPages or filterList.totalPages == 1}">
                            <li class="disabled"><a href="#">&gt;</a></li>
                            <li class="disabled"><a href="#">&gt;&gt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${nextUrl}">&gt;</a></li>
                            <li><a href="${lastUrl}">&gt;&gt;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                
                
                function destroy(){
                    var url = $(this).attr("href");
                    var caller = this;
                    $.ajax({
                        url: url,
                        type: "DELETE"
                    }).done(function(data){
                        $(caller).parents("section.filter-item").fadeOut("slow", function(){
                            $(this).remove();
                            $("#filter-list-container").masonry( 'reload' );
                        });
                    });
                }
                
                $("#destroy").live('click',destroy);
                
                $("#filter-list-container").masonry({
                    // options
                    itemSelector : '.filter-item',
                    animated: true
                    //                    columnWidth : 200
                });
                
                

            });
        </script>
    </body>
</html>
