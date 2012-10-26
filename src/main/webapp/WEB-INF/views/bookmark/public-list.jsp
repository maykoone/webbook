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
        <title>Lista de favoritos</title>
        <!--<link rel="stylesheet" href="css/tagify-style.css" />-->
        <!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css" />-->
        <!--<link rel="stylesheet" href="resources/css/bootstrap.css" />-->


    </head>
    <c:set var="currentUrl" value="${pageContext.request.contextPath}/bookmarks/${userSearch.userName}" />
    <c:set var="rootUrl" value="${pageContext.request.contextPath}" />
    <body>
        <div class="grid_12">
            <section class="user-details wb-box-with-shadow popular-content">
                <a href="" class="avatar">
                    <!-- <img src="http://springpad.com/api/users/maykoone/photo?w=80&h=80" alt="avatar" /> -->
                    <wb:gravatar email="${userSearch.email}" />
                </a>
                <div class="user-info">
                    <a hreaf="" class="wb-font-big">
                        <strong>@${userSearch.userName}</strong>
                    </a>&nbsp;
                    <c:if test="${not empty userSearch.name}">
                        <span class="wb-font-big">[${userSearch.name}]</span>
                    </c:if>
                    <ul class="user-stats">
                        <li class="wb-font-small"><a href="" data-placement="bottom"  rel="tooltip" title="favoritos de ${userSearch.userName}"><strong>${bookmarkList.totalElements}</strong> Bookmarks</a></li>
                        <li class="wb-font-small"><a href="${rootUrl}/users/${userSearch.userName}/following" data-placement="bottom"  rel="tooltip" title="Pessoas que ${userSearch.userName} está seguindo"><strong>${fn:length(userSearch.followings)}</strong> seguindo</a></li>
                        <li class="wb-font-small"><a href="${rootUrl}/users/${userSearch.userName}/followers" data-placement="bottom"  rel="tooltip" title="Pessoas que seguem ${userSearch.userName}"><strong>${fn:length(userSearch.followers)}</strong> seguidores</a></li>
                        <c:choose>
                            <c:when test="${userInstance.isFollowing(userSearch)}">
                                <li class="wb-font-small"><strong><a href="${rootUrl}/users/${userSearch.userName}/follow" class="btn btn-info btn-mini">Seguir</a></strong></li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <form:form action="${rootUrl}/users/${userSearch.userName}/unfollow" method="delete">
                                        <input type="hidden" name="friendship" value="${userSearch.id}">
                                        <button class="btn btn-mini btn-danger">Parar de seguir</button>
                                    </form:form>
                                </li>
                            </c:otherwise>
                        </ul>
                    </div>
                </section>
            </div>
            <div class="clear"></div>
            <div class="grid_8">
                <section id="user-list-bookmark" class="wb-box-with-shadow popular-content">
                    <div class="list-header">
                        <h4 class="wb-left-float">Favoritos do ${userSearch.userName}</h4>
                    </div>
                    <c:forEach items="${bookmarkList.content}" var="bookmark">
                        <div class="bookmark-item">
                            <a href="" class="bookmark-thumbnail">
                                <div class="default-thumb"></div>
                            </a>
                            <div class="bookmark-info">
                                <h3><a href="" class="bookmark-title wb-font-medium">${bookmark.title}</a></h3>
                                <a href="" class="bookmark-url wb-font-small">${bookmark.url}</a>
                                <p class="wb-font-small">${bookmark.description}</p>
                                <ul class="bookmark-tag-list">
                                    <c:forEach items="${bookmark.tags}" var="tag">
                                        <li><a href="${currentUrl}/tags/${fn:replace(tag, " ", "-")}"><span class="tag">#${tag}</span></a></li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="bookmark-item-control">
                                <ul>
                                    <li><a href="${rootUrl}/bookmarks/${bookmark.id}/edit" id="editMe" onclick="return false;" title="Adicione ao seus favoritos"><i class="icon-bookmark"></i>Salvar</a></li>
                                    <li><a href=""><i class="icon-comment"></i>${fn:length(bookmark.comments)} Comentários</a></li>
                                    <li><a href=""><i class="icon-share"></i>Compartilhar</a></li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                    <c:url var="firstUrl" value="${userSearch.userName}?page=1" />
                    <c:url var="lastUrl" value="${userSearch.userName}?page=${bookmarkList.totalPages}" />
                    <c:url var="prevUrl" value="${userSearch.userName}?page=${currentIndex - 1}" />
                    <c:url var="nextUrl" value="${userSearch.userName}?page=${currentIndex + 1}" />
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
                                <c:url var="pageUrl" value="${userSearch.userName}?page=${i}" />
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
                                <c:when test="${currentIndex == bookmarkList.totalPages}">
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
                </section>
            </div>
            <div class="grid_4">
                <div class="wb-box-with-shadow popular-content">
                    <h4><i class="icon-tags"></i>Tags Usadas</h4>
                </div>
            </div>

            <div id="add-bookmark-modal" class="modal hide fade" style="display: none">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h4>Adicionar Favorito</h4>
                </div>
                <div class="modal-body">
                    <form:form action="${rootUrl}/bookmarks" commandName="bookmark" method="post" id="add-bookmark-form">
                        <form:hidden path="id" id="id" />
                        <div class="loading" style="display: none"><img class="ajax-loader" src="resources/img/ajax-loader.gif" /></div>
                        <div class="modal-body">
                            <fieldset class="bookmark-form">
                                <form:hidden path="id" id="id" />
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Título</label>
                                    </div>
                                    <div class="field-input">
                                        <input class="input-xxlarge" type="text" name="title" id="title">
                                        <span class="help-block">Título do link</span>
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Url</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="url" />
                                        <input class="input-xxlarge" type="text" name="url" id="url">
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Descrição</label>
                                    </div>
                                    <div class="field-input">
                                        <span class="char-counter">0</span>
                                        <textarea maxlength="140" class="input-xxlarge" name="description" id="description" rows="3"></textarea>
                                        <span class="help-block">Se quiser informe uma descrição com no máximo 140 caracteres</span>
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Tags</label>
                                    </div>
                                    <div class="field-input">
                                        <input class="input-xxlarge" type="text" name="tags" id="tags">
                                        <span class="help-block">Tags são palavras chaves, informe quantas quiser separadas por vírgula</span>
                                    </div>
                                </div>

                                <div class="field-block">							

                                    <label class="wb-font-small checkbox">
                                        <form:checkbox path="privateBookmark" id="privateBookmark"/>
                                        <strong>Privado</strong><i class="icon-lock"></i>
                                    </label>
                                </div>
                            </fieldset>
                        </div>
                        <div class="modal-footer">
                            <div class="controls">
                                <div class="control">
                                    <button id="btn-bookmark-modal-cancel" class="btn" data-dismiss="modal" type="reset">Cancelar</button>
                                    <button id="btn-bookmark-modal-post" type="submit" class="btn btn-primary">Adicionar favorito</button>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
            <!--        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
                    <script type="text/javascript" src="resources/js/bootstrap.js"></script>-->
            <!--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/jquery-ui.min.js"></script>-->
            <!--<script type="text/javascript" src="js/jquery.simplemodal.1.4.2.min.js"></script>-->
            <script type="text/javascript">
                $(document).ready(function() {

                    //twitter bootstrap modal
                    function openModal(){
                        $('#add-bookmark-modal').modal();
                    }

                    function loadData(){
                        var url = $(this).attr("href");
                        $.get(url, {}, function (data){
                            $("#id").val(data.id);
                            $("#title").val(data.title);
                            $("#url").val(data.url);
                            $("#description").val(data.description);
                            $("#tags").val(data.tags);
                            data.privateBookmark?$("#privateBookmark").attr("checked", "checked"):$("privateBookmark").removeAttr("checked");
                            openModal();
                        });
                    }
                
                
                
                    $("#editMe").live('click',loadData);

                });
            </script>

        </body>


    </html>

