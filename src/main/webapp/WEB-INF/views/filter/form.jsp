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
                        <li class="wb-font-small"><a href="/bookmarks" data-placement="bottom"  rel="tooltip" title="Seus favoritos">${bookmarkCount} Bookmarks</a></li>
                        <li class="wb-font-small"><a href="/users/followings" data-placement="bottom"  rel="tooltip" title="Veja quais amigos você acompanha">${fn:length(userInstance.followings)} Amigos que você acompanha</a></li>
                        <li class="wb-font-small"><a href="/users/followers" data-placement="bottom"  rel="tooltip" title="Veja quais amigos você acompanha">${fn:length(userInstance.followers)} Amigos que te acompanham</a></li>
                        <li class="wb-font-small"><a href="/filters">${filterCount} Filtros</a></li>
                        <li class="wb-font-small"><strong><a href="/users/account/profile" class="btn btn-mini">Edite seu Perfil</a></strong></li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="clear"></div>
        <div class="grid_8" >
            <div class="wb-box-with-shadow">
                <form:form action="/filters" commandName="filterInstance" method="${formMethod}" id="add-filter-form" cssClass="form-horizontal form">
                    <legend>Cadastro de filtro</legend>
                    <div class="loading" style="display: none"><img class="ajax-loader" src="" /></div>
                    <input type="hidden" name="id" id="id"/>
                    <div class="field-block">
                        <div class="field-title">
                            <label>Título</label>
                        </div>
                        <div class="field-input">
                            <form:errors path="title" cssClass="error" />
                            <input class="input-xxlarge" type="text" id="title" name="title" value="${filterInstance.title}" />
                            <span class="help-block">Dê um título ao seu filtro (Obrigatório)</span>
                        </div>
                    </div>
                    <div class="field-block">
                        <div class="field-title">
                            <form:errors path="description" cssClass="error" />
                            <label>Descrição</label>
                        </div>
                        <div class="field-input">
                            <textarea class="input-xxlarge" rows="3" cols="4" id="description" name="description"></textarea>
                            <span class="help-block">Informe uma descrição</span>
                        </div>
                    </div>
                    <div class="field-block">
                        <div class="field-title">
                            <label>Tags</label>
                        </div>
                        <div class="field-input">
                            <form:errors path="tags" cssClass="error" />
                            <input class="input-xxlarge"type="text" name="tags" id="tags" id="tags" />
                            <span class="help-block">Todos os links que correspondem a essas tags serão visualizadas através desse filtro</span>
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
                <h4>Seus filtros</h4>
                <p class="wb-font-medium" ></p>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function(){
                $("div.loading").ajaxStart(function() {
                    $(this).show();
                });
                $("div.loading").ajaxComplete(function() {
                    $(this).hide();
                });
            <c:if test="${filterId ne null}">
                
                    $.get('/filters/${filterId}', function(data){
                        $("#id").val(data.id);
                        $("#title").val(data.title);
                        $("#description").val(data.description);
                        $("#tags").val(data.tags);
                        $("#tags").importTags(data.tags.toString());
                    });
            </c:if>
                    $("#tags").tagsInput({
                        'height':'auto',
                        'width':'530px',
                        'defaultText':'add uma tag'
                    });
                
                
                    $("#add-filter-form").validate({
                        rules: {
                            title :{
                                required: true,
                                maxlength: 50
                            },
                            description:{
                                maxlength: 140
                            },
                            tags: {
                                required: true
                            }
                        },
                        messages: {
                            title :{
                                required: "O campo título não pode ser vazio",
                                maxlength: "O tamanho máximo do título é de 50 caracteres"
                            },
                            description:{
                                maxlength: "O campo descrição deve ter no máximo 140 caracteres"
                            },
                            tags: {
                                required: "Adicione pelo menos uma tag esse filtro"
                            }
                        },
                        errorPlacement: function(error, element) {
                            error.insertBefore(element);
                        }
                    });
                
                })
        </script>
    </body>
</html>
