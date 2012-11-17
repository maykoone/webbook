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
<!DOCTYPE html>
<html>
    <head>
        <title>Filtro de favoritos</title>
        <!--<link rel="stylesheet" href="css/tagify-style.css" />-->
        <!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css" />-->
        <!--<link rel="stylesheet" href="resources/css/bootstrap.css" />-->


    </head>
    <c:url var="currentUrl" value="bookmarks" />
    <c:url var="rootUrl" value="${pageContext.request.contextPath}" />
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
                        <li class="wb-font-small"><a href="${pageContext.request.contextPath}/bookmarks" data-placement="bottom"  rel="tooltip" title="Seus favoritos">${bookmarkCount} Bookmarks</a></li>
                        <li class="wb-font-small"><a href="${pageContext.request.contextPath}/users/followings"  data-placement="bottom"  rel="tooltip" title="Veja quais amigos você acompanha">${fn:length(userInstance.followings)} Seguindo</a></li>
                        <li class="wb-font-small"><a href="${pageContext.request.contextPath}/users/followers"data-placement="bottom"  rel="tooltip" title="Veja quais pessoas acompanham você">${fn:length(userInstance.followers)} Seguidores</a></li>
                        <li class="wb-font-small"><a href="${pageContext.request.contextPath}/filters">${filterCount} filtros</a></li>
                        <li class="wb-font-small"><strong><a href="${pageContext.request.contextPath}/users/account/profile" class="btn btn-mini"><i class="icon-user"></i>Edite seu Perfil</a></strong></li>
                    </ul>
                </div>
                <wb:message messageBean="${message}" />
            </section>
        </div>
        <div class="clear"></div>
        <div class="grid_8">
            <section id="user-list-bookmark" class="wb-box-with-shadow popular-content">
                <div class="list-header">
                    <h4 class="wb-left-float">Favoritos do filtro ${filterInstance.title}</h4>
                    <a href="${pageContext.request.contextPath}/filters/${filterInstance.id}/edit" class="btn btn-primary btn-mini wb-right-float">
                        <i class="icon-plus icon-white"></i>Editar filtro
                    </a>
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
                                    <li><html:tagLink tag="${tag}"/></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="bookmark-item-control">
                            <ul>
                                <li>
                                    <a href="${pageContext.request.contextPath}/ajax/bookmarks/${bookmark.id}/comments" data-toggle="modal" onclick="return false;" class="get-comments">
                                        <i class="icon-comment"></i>Comentários
                                    </a>
                                </li>
                                <li><a href="#" class="share-button" data-url="${bookmark.url}" data-title="${bookmark.title}" data-username="${userInstance.userName}"><i class="icon-share"></i>Compartilhar</a></li>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
                <c:url var="firstUrl" value="bookmarks?page=1" />
                <c:url var="lastUrl" value="bookmarks?page=${bookmarkList.totalPages}" />
                <c:url var="prevUrl" value="bookmarks?page=${currentIndex - 1}" />
                <c:url var="nextUrl" value="bookmarks?page=${currentIndex + 1}" />
                <div class="pagination pagination-centered">
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
                            <c:url var="pageUrl" value="bookmarks?page=${i}" />
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
            <div class="wb-box-with-shadow popular-content" id="comments">
                <h4>${filterInstance.title}</h4>
                <p class="wb-font-medium" >${filterInstance.description}</p>
                <ul class="filter-tag-list">
                    <c:forEach items="${filterInstance.tags}" var="tag">
                        <li><a href="tags/${tag}"><span class="tag">#${tag}</span></a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div id="comments-modal" class="modal hide fade">  
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4>Comentários</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" type="reset">Fechar</button>
            </div>
        </div>
        <div id="share-modal" class="modal hide fade">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4>Compartilhar Favorito</h4>
            </div>
            <div class="modal-body">
                <h5><span class="share-bookmark-title"></span></h5>
                <p><a href="" class="share-bookmark-url"></a></p>
                <hr />
                <ul class="share-actions">
                    <li id="twitter-share"></li>
                    <hr />
                    <li id="gplus-share">
                        <a class="gplus-share-button" href="" onclick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;">
                            <img  src="https://www.gstatic.com/images/icons/gplus-32.png" alt="Share on Google+"/>
                        </a>
                        <p>Compartilhar isto no Google+</p>
                    </li>
                    <hr />
                    <li>
                        <iframe class="facebook-share-button" src="" scrolling="no" frameborder="0" style="border:none; width:450px; height:80px"></iframe>
                    </li>
                    <hr />
                </ul>
            </div>
            <div class="modal-footer">
                <div class="controls">
                    <div class="control">
                        <button id="btn-bookmark-modal-cancel" class="btn" data-dismiss="modal" type="reset">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>


        <div id="comments-modal" class="modal hide">  
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4>Comentários</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <a href="#" class="btn">Fechar</a>
            </div>
        </div>
        <!--        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
                <script type="text/javascript" src="resources/js/bootstrap.js"></script>-->
        <!--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/jquery-ui.min.js"></script>-->
        <!--<script type="text/javascript" src="js/jquery.simplemodal.1.4.2.min.js"></script>-->
        <script type="text/javascript">
            $(document).ready(function() {
                function openCommentsModal(){
                    $("#comments-modal").modal()
                }
                
                $(".share-button").on('click', function(){
                    var shareUrl = $(this).data('url');
                    var shareUrlText = $(this).data('title');
                    var gplusUrl = "https://plus.google.com/share?url=" + shareUrl;
                    var facebookUrl = "https://www.facebook.com/plugins/like.php?href=" + shareUrl;
                    
                    $("#twitter-share").html('<a href="https://twitter.com/share" data-text="'+shareUrlText+'" data-url="'+ shareUrl +'" class="twitter-share-button" data-lang="pt" data-size="large" data-hashtags="bookmark">Tweetar</a>');
                    
                    $(".gplus-share-button").attr('href', gplusUrl);
                    $(".facebook-share-button").attr('src', facebookUrl);
                    $(".share-bookmark-title").text(shareUrlText);
                    $(".share-bookmark-url").text(shareUrl);
                    $(".share-bookmark-url").attr("href", shareUrl);
                    
                    twttr.widgets.load();
                    $("#share-modal").modal();
                    
                });
                
               
                
                function loadComments(){
                    var url = $(this).attr("href");
                    $("#comments-modal .modal-body").load(url);
                    openCommentsModal();
                }
                
                
                $(".get-comments").live('click',loadComments);
              
            });
        </script>

    </body>


</html>

