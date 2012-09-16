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
                        <li class="wb-font-small"><a href="${pageContext.request.contextPath}/bookmarks" data-placement="bottom"  rel="tooltip" title="Seus favoritos">${bookmarkList.totalElements} Bookmarks</a></li>
                        <li class="wb-font-small"><a href="users/followings"  data-placement="bottom"  rel="tooltip" title="Veja quais amigos você acompanha">${fn:length(userInstance.followings)} Seguindo</a></li>
                        <li class="wb-font-small"><a href="users/followers"data-placement="bottom"  rel="tooltip" title="Veja quais pessoas acompanham você">${fn:length(userInstance.followers)} Seguidores</a></li>
                        <li class="wb-font-small"><a href="${pageContext.request.contextPath}/filters">10 Filtros</a></li>
                        <li class="wb-font-small"><strong><a href="users/account/profile" class="btn btn-mini"><i class="icon-user"></i>Edite seu Perfil</a></strong></li>
                    </ul>
                </div>
                <wb:message messageBean="${message}" />
            </section>
        </div>
        <div class="clear"></div>
        <div class="grid_8">
            <section id="user-list-bookmark" class="wb-box-with-shadow popular-content">
                <div class="list-header">
                    <h4>Seus favoritos</h4>
                    <div class="bookmark-preview">
                        <div class="input-append">
                            <input class="input-xxlarge" id="input-bookmark-preview" placeholder="Cole uma url" type="text">
                            <button id="btn-bookmark-preview" class="btn btn-primary" rel="tooltip" title="Adicione um favorito" type="button"><i class="icon-plus icon-white"></i></button>
                        </div>
                    </div>
                    <!--                    <a href="#add-bookmark-modal" class="btn btn-primary btn-mini wb-right-float" id="add-bookmark" data-toggle="modal">
                                            <i class="icon-plus icon-white"></i>Adicionar Favorito
                                        </a>-->
                </div>
                <c:forEach items="${bookmarkList.content}" var="bookmark">
                    <div class="bookmark-item">
                        <a href="" class="bookmark-thumbnail">
                            <div class="default-thumb"></div>
                        </a>
                        <div class="bookmark-info">
                            <p><a href="" class="bookmark-title wb-font-medium">${bookmark.title}&nbsp;<c:if test="${bookmark.privateBookmark}"><i class="icon-lock"></i></c:if></a><p>
                                <a href="" class="bookmark-url wb-font-small">${bookmark.url}</a>
                            <p class="wb-font-small">${bookmark.description}</p>
                            <ul class="bookmark-tag-list">
                                <c:forEach items="${bookmark.tags}" var="tag">
                                    <li><a href="${currentUrl}/tags/${tag}"><span class="label label-success">${tag}</span></a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="bookmark-item-control">
                            <ul>
                                <li><a href="${currentUrl}/${bookmark.id}" id="destroy" onclick="return false;"><i class="icon-remove"></i>Excluir</a></li>
                                <li><a href="${currentUrl}/${bookmark.id}/edit" id="editMe" onclick="return false;"><i class="icon-edit"></i>Editar</a></li>
                                <c:if test="${not bookmark.privateBookmark}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/ajax/bookmarks/${bookmark.id}/comments" data-toggle="modal" onclick="return false;" id="get-comments">
                                            <i class="icon-comment"></i>Comentários
                                        </a>
                                    </li>
                                </c:if>
                                <li><a href=""><i class="icon-share"></i>Compartilhar</a></li>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
                <c:url var="firstUrl" value="bookmarks?page=1" />
                <c:url var="lastUrl" value="bookmarks?page=${bookmarkList.totalPages}" />
                <c:url var="prevUrl" value="bookmarks?page=${currentIndex - 1}" />
                <c:url var="nextUrl" value="bookmarks?page=${currentIndex + 1}" />
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
            <div class="wb-box-with-shadow popular-content">
                <div class="top-tags">
                    <h4><i class="icon-tags"></i> Tags usadas</h4>
                    <ul></ul>
                </div>

            </div>
        </div>

        <div id="add-bookmark-modal" class="modal hide" style="display: none">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4>Adicionar Favorito</h4>

            </div>
            <form:form action="${currentUrl}" commandName="bookmark" method="post" id="modal-form">
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
                
                
                //                function openModal(){
                //                    $('#add-bookmark-modal').modal({
                //                        opacity:60,
                //                        overlayCss: {backgroundColor:"#fff"},
                //                        overlayClose:true
                //                
                //                    });
                //                }
                //twitter bootstrap modal
                function openModal(){
                    $('#add-bookmark-modal').modal();
                }
                
                function openCommentsModal(){
                    $("#comments-modal").modal()
                }
                
                
                //                $('#add-bookmark').click( function(){ 
                //                    openModal();
                //                });
                function loadData(){
                    var url = $(this).attr("href");
                    $.get(url, function(data){
                        $("#id").val(data.id);
                        $("#title").val(data.title);
                        $("#url").val(data.url);
                        $("#description").val(data.description);
                        $("#tags").val(data.tags);
                        data.privateBookmark?$("#privateBookmark").attr("checked", "checked"):$("#privateBookmark").removeAttr("checked");
                        openModal();
                    });
                }
                
                function destroy(){
                    var url = $(this).attr("href");
                    var caller = this;
                    $.ajax({
                        url: url,
                        type: "DELETE"
                    }).done(function(data){
                        if(console && console.log){
                            console.log($(caller).parents("div.bookmark-item"))
                            console.log(caller)
                        }
                        $(caller).parents("div.bookmark-item").fadeOut("slow", function(){
                            $(this).remove();
                        });
                    });
                }
                
                function loadComments(){
                    var url = $(this).attr("href");
                    $("#comments-modal .modal-body").load(url);
                    openCommentsModal();
                }
                
                
                $("#editMe").live('click',loadData);
                $("#get-comments").live('click',loadComments);
                $("#destroy").live('click',destroy);
                
                $.getJSON("${pageContext.request.contextPath}/search/ranking/${userInstance.userName}", function(data) {
                    var items = [];

                    $.each(data, function(key, val) {
                        
                        $('div.top-tags ul').prepend('<li><span class="label label-success">'+ key +'</span> <span class="badge">'+ val + '</span></li>');
                        //                        items.push('<li>'+ key +' ('+ val +')'+ '</li>');
                    });

                    
                });
                
                $("#btn-bookmark-preview").click(function(){
                    var url =  $("#input-bookmark-preview").val();
                    
                    $("fieldset.bookmark-form").hide();
                    $("div.loading").show();
                    $("#btn-bookmark-modal-post").addClass("disabled");
                    $("#btn-bookmark-modal-post").attr("disabled", "disabled");
                    $("#btn-bookmark-modal-post").text("Carregando...");
                        
                    openModal();
                    
                    $.getJSON('${pageContext.request.contextPath}/bookmarks/scraping',{url: url} , function(data){
                        
                        $("#title").val(data.title);
                        $("#url").val(data.url);
                        $("#description").val(data.description);
                        $(".char-counter").text(data.description.length);
                        $("#tags").val(data.tags);
                        
                        $("fieldset.bookmark-form").show();
                        $("div.loading").hide();
                        $("#btn-bookmark-modal-post").removeClass("disabled");
                        $("#btn-bookmark-modal-post").removeAttr("disabled");
                        $("#btn-bookmark-modal-post").text("Adicionar Favorito");
                        
                    });
                   
                });
                $("textarea#description").keyup(function(){
                    $(".char-counter").text($(this).val().length);
                });
                
                

            });
        </script>

    </body>


</html>

