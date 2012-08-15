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
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Lista de favoritos</title>
        <!--<link rel="stylesheet" href="css/tagify-style.css" />-->
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/base/jquery-ui.css" />
        <link rel="stylesheet" href="css/bootstrap.css" />


    </head>

    <body>

        <div class="grid_12">
            <section class="user-details wb-box-with-shadow popular-content">
                <a href="" class="avatar">
                    <!-- <img src="http://springpad.com/api/users/maykoone/photo?w=80&h=80" alt="avatar" /> -->
                    <img src="http://springpad.com/wp-content/themes/springpad/assets/homepage/images/avatars/scene1-3.jpg" alt="avatar" />
                </a>
                <div class="user-info">
                    <a hreaf="" class="wb-font-big"><strong>@<sec:authentication property="principal.username" /></strong></a>&nbsp;<span class="wb-font-big">[Mayko B. Oliveira]</span>
                    <ul class="user-stats">
                        <li class="wb-font-small"><a href="">${fn:length(bookmarkList.content)} Bookmarks</a></li>
                        <li class="wb-font-small"><a href="">10 Amigos que você acompanha</a></li>
                        <li class="wb-font-small"><a href="">10 Amigos que te acompanham</a></li>
                        <li class="wb-font-small"><a href="">10 Filtros</a></li>
                        <li class="wb-font-small"><strong><a href="">Edite seu Perfil</a></strong></li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="clear"></div>
        <div class="grid_8">
            <section id="user-list-bookmark" class="wb-box-with-shadow popular-content">
                <h4>Seus favoritos</h4>
                <a href="#add-bookmark-modal" id="add-bookmark" data-toggle="modal">
                    Adicionar Favorito
                </a>
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
                                    <li><a href="">#${tag}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="bookmark-item-control">
                            <ul>
                                <li><a href="bookmarks/${bookmark.id}" id="destroy" onclick="return false;">Excluir</a></li>
                                <li><a href="bookmarks/${bookmark.id}" id="editMe" onclick="return false;">Editar</a></li>
                                <li><a href="">10 Comentários</a></li>
                                <li><a href="">Compartilhar</a></li>
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
                <h4>Lorem Ipsum</h4>
                <p class="wb-font-medium">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."</p>
            </div>
        </div>

        <div id="add-bookmark-modal" class="wb-box-with-shadow" style="display:none">
            <h4>Adicionar Favorito</h4>
            <form:form action="${pageContext.request.contextPath}/bookmarks" commandName="bookmark" method="post" id="add-bookmark-form">
                <form:hidden path="id" id="id" />
                <div class="field-block">
                    <div class="field-title">
                        <label>Título</label>
                    </div>
                    <div class="field-input">
                        <input class=""type="text" name="title" id="title">
                    </div>
                </div>
                <div class="field-block">
                    <div class="field-title">
                        <label>Url</label>
                    </div>
                    <div class="field-input">
                        <form:errors path="url" />
                        <input class=""type="text" name="url" id="url">
                    </div>
                </div>
                <div class="field-block">
                    <div class="field-title">
                        <label>Descrição</label>
                    </div>
                    <div class="field-input">
                        <textarea name="description" id="description"></textarea>
                    </div>
                </div>
                <div class="field-block">
                    <div class="field-title">
                        <label>Tags</label>
                    </div>
                    <div class="field-input">
                        <input class=""type="text" name="tags" id="tags">
                    </div>
                </div>

                <div class="field-block">							
                    <form:checkbox path="visible" id="visible"/>
                    <label class="wb-font-small"><strong>Privado</strong></label>
                </div>
                <div class="controls">
                    <div class="control">
                        <input id="button-register" class="wb-border-radius-all" type="submit" value="Adicionar Favorito">
                    </div>
                </div>
            </form:form>
        </div>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <!--<script type="text/javascript" src="js/bootstrap.js"></script>-->
        <!--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/jquery-ui.min.js"></script>-->
        <script type="text/javascript" src="js/jquery.simplemodal.1.4.2.min.js"></script>
        <!--<script type="text/javascript" src="js/jquery.tagify.js"></script>-->
        <script type="text/javascript">
            $(document).ready(function() {
                
                
                function openModal(){
                    $('#add-bookmark-modal').modal({
                        opacity:60,
                        overlayCss: {backgroundColor:"#fff"},
                        overlayClose:true
                
                    });
                }
                $('#add-bookmark').click( function(){ 
                    openModal();
                });
                
                //                $('textarea#tags').tagify({outputDelimiter: ','});
                function loadData(){
                    var url = $(this).attr("href");
                    $.get(url, {}, function (data){
                        $("#id").val(data.id);
                        $("#title").val(data.title);
                        $("#url").val(data.url);
                        $("#description").val(data.description);
                        $("#tags").val(data.tags);
                        //                        $("#visible").val(data.reply.telefone);
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
                        //$(caller).parents("div.bookmark-item").remove();
                    });
                }
                
                $("#editMe").live('click',loadData);
                $("#destroy").live('click',destroy);

            });
        </script>

    </body>


</html>

