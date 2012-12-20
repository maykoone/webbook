<%-- 
    Document   : list
    Created on : 11/08/2012, 17:47:37
    Author     : maykoone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/support" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Lista de favoritos</title>


    </head>
    <c:url var="currentUrl" value="bookmarks" />
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
                        <li class="wb-font-small"><a href="bookmarks" data-placement="bottom"  rel="tooltip" title="Seus favoritos">${bookmarkList.totalElements} Bookmarks</a></li>
                        <li class="wb-font-small"><a href="users/followings"  data-placement="bottom"  rel="tooltip" title="Veja quais amigos você acompanha">${fn:length(userInstance.followings)} Seguindo</a></li>
                        <li class="wb-font-small"><a href="users/followers" data-placement="bottom"  rel="tooltip" title="Veja quais pessoas acompanham você">${fn:length(userInstance.followers)} Seguidores</a></li>
                        <li class="wb-font-small"><a href="filters">${filterCount} filtros</a></li>
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
                        <form action="#" id="preview-bookmark">
                            <div class="input-append">
                                <input class="input-xxlarge assist" name="previewurl" id="input-bookmark-preview" placeholder="Cole uma url" type="text" data-title="Adicionar favorito" data-content="Cole ou digite uma url (http://example.com) para adicionar um favorito" data-placement="bottom">
                                <button id="btn-bookmark-preview" type="submit" class="btn btn-primary" rel="tooltip" title="Adicione um favorito" type="button"><i class="icon-plus icon-white"></i></button>
                            </div>
                        </form>
                    </div>
                    <!--                    <a href="#add-bookmark-modal" class="btn btn-primary btn-mini wb-right-float" id="add-bookmark" data-toggle="modal">
                                            <i class="icon-plus icon-white"></i>Adicionar Favorito
                                        </a>-->
                </div>

                <c:forEach items="${bookmarkList.content}" var="bookmark">
                    <div class="bookmark-item">
                        <a href="" class="bookmark-thumbnail">
                            <div class="default-thumb" <c:if test="${not empty bookmark.iconPath}">style="background-image:url('${bookmark.iconPath}')"</c:if>></div>
                        </a>
                        <div class="bookmark-info">
                            <p>
                                <a href="${bookmark.url}" target="_blank" class="bookmark-title wb-font-medium">${bookmark.title}&nbsp;<c:if test="${bookmark.privateBookmark}"><i class="icon-lock"></i></c:if></a>&nbsp;<small><fmt:formatDate value="${bookmark.creationDate}" pattern="d MMM, yyyy"/></small>
                            <p>
                                <a href="${bookmark.url}" target="_blank" class="bookmark-url wb-font-small">${bookmark.url}</a>
                            <p class="wb-font-small">${bookmark.description}</p>
                            <ul class="bookmark-tag-list">
                                <c:forEach items="${bookmark.tags}" var="tag">
                                    <li><html:tagLink tag="${tag}"/></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="bookmark-item-control">
                            <ul>
                                <li><a href="${currentUrl}/${bookmark.id}" class="destroy" onclick="return false;"><i class="icon-remove"></i>Excluir</a></li>
                                <li><a href="${currentUrl}/${bookmark.id}/edit" class="editMe" onclick="return false;"><i class="icon-edit"></i>Editar</a></li>
                                <c:if test="${not bookmark.privateBookmark}">
                                    <li>
                                        <a href="ajax/bookmarks/${bookmark.id}/comments" data-toggle="modal" onclick="return false;" class="get-comments">
                                            <i class="icon-comment"></i>Comentários
                                        </a>
                                    </li>
                                </c:if>
                                <li><a href="#" class="share-button" data-url="${bookmark.url}" data-title="${bookmark.title}" data-username="${userInstance.userName}"><i class="icon-share"></i>Compartilhar</a></li>
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

        <div id="add-bookmark-modal" class="modal hide fade" style="display: none">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4>Adicionar Favorito</h4>

            </div>
            <form:form action="${currentUrl}" commandName="bookmark" method="post" id="modal-form">
                <div class="loading" style="display: none"><img class="ajax-loader" src="/resources/img/ajax-loader.gif" /></div>
                <div class="modal-body">
                    <fieldset class="bookmark-form">
                        <form:hidden path="id" id="id" />
                        <form:hidden path="iconPath" id="icon" />
                        <div class="field-block">
                            <div class="field-title">
                                <label>Título</label>
                            </div>
                            <div class="field-input">
                                <input class="input-xxlarge" type="text" name="title" id="title" >
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
        <!--        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
                <script type="text/javascript" src="resources/js/bootstrap.js"></script>-->
        <!--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/jquery-ui.min.js"></script>-->
        <!--<script type="text/javascript" src="js/jquery.simplemodal.1.4.2.min.js"></script>-->
        <script type="text/javascript">
            $(document).ready(function() {
                $(".assist").focus(function(){
                    $(this).popover({delay: { show: 500, hide: 100 }});
                });
                

                //twitter bootstrap modal
                function openModal(){
                    $('#add-bookmark-modal').modal();
                }
                
                function openCommentsModal(){
                    $("#comments-modal").modal()
                }

                function loadData(){
                    var url = $(this).attr("href");
                    $.get(url, function(data){
                        $("#id").val(data.id);
                        $("#icon").val(data.iconPath);
                        $("#title").val(data.title);
                        $("#url").val(data.url);
                        $("#description").val(data.description);
                        $("#tags").val(data.tags);
                        $("#tags").importTags(data.tags.toString());
                        data.privateBookmark?$("#privateBookmark").attr("checked", "checked"):$("#privateBookmark").removeAttr("checked");
                        openModal();
                    });
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
                
                function destroy(){
                    var url = $(this).attr("href");
                    var caller = this;
                    $.ajax({
                        url: url,
                        type: "DELETE"
                    }).done(function(data){
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
                
                
                $(".editMe").on('click',loadData);
                $(".get-comments").live('click',loadComments);
                $(".destroy").on('click',destroy);
                
                $.getJSON("search/ranking/${userInstance.userName}", function(data) {
                    var items = [];

                    $.each(data, function(key, val) {
                        
                        $('div.top-tags ul').prepend('<li><span class="label label-success">'+ key +'</span> <span class="badge">'+ val + '</span></li>');
                    });

                    
                });
                
                
                function previewBookmark(){
                
                    var url =  $("#input-bookmark-preview").val();
                    
                    $("fieldset.bookmark-form").hide();
                    $("div.loading").show();
                    $("#btn-bookmark-modal-post").addClass("disabled");
                    $("#btn-bookmark-modal-post").attr("disabled", "disabled");
                    $("#btn-bookmark-modal-post").text("Carregando...");
                        
                    openModal();
                    
                    $.getJSON('bookmarks/scraping',{url: url} , function(data){
                        
                        $("#icon").val(data.iconPath);
                        $("#title").val(data.title);
                        $("#url").val(data.url);
                        $("#description").val(data.description);
                        if(data.description != null){
                            $(".char-counter").text(data.description.length);
                        }
                        if(data.tags != null){
                            $("#tags").importTags(data.tags.toString());
                        }
                        
                        $("fieldset.bookmark-form").show();
                        $("div.loading").hide();
                        $("#btn-bookmark-modal-post").removeClass("disabled");
                        $("#btn-bookmark-modal-post").removeAttr("disabled");
                        $("#btn-bookmark-modal-post").text("Adicionar Favorito");
                        
                    });
                }
                
                //$("#btn-bookmark-preview").click(previewBookmark);
                
                $("#preview-bookmark").validate({
                    rules: {
                        previewurl :{
                            required: true,
                            url: true
                        }
                    },
                    messages: {
                        previewurl: {
                            required: "Você precisa postar uma URL para adicionar um favorito!",
                            url: "Esse valor não parece uma URL válida."
                        }
                    },
                    submitHandler: previewBookmark,
                    errorPlacement: function(error, element) {
                        error.insertBefore(element);
                    }
                });
                
                $("textarea#description").keyup(function(){
                    $(".char-counter").text($(this).val().length);
                });
                
                $("#tags").tagsInput({
                    'height':'auto',
                    'width':'auto',
                    'defaultText':'add uma tag'
                });
                
                $("#modal-form").validate({
                    rules: {
                        url:{
                            required: true,
                            url: true
                        },
                        description: {
                            maxlength: 140
                        }
                    },
                    messages: {
                        url: {
                            required: "URL é um campo obrigatório",
                            url: "O Valor informado não é uma URL válida"
                        },
                        description: {
                            maxlength: "A descrição deve ter no máximo 140 caracteres"
                        }
                    }
                });

            });
        </script>

    </body>


</html>

