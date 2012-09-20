<%-- 
    Document   : edit
    Created on : 18/08/2012, 11:42:56
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
                   <wb:gravatar email="${userInstance.email}" />
                </a>
                <div class="user-info">
                    <a hreaf="" class="wb-font-big"><strong><sec:authentication property="principal.username" /></strong></a>&nbsp;<span class="wb-font-big">${userInstance.name}</span>
                    <ul class="user-stats">
                        <li class="wb-font-small"><a href="">10 Bookmarks</a></li>
                        <li class="wb-font-small"><a href="">10 Amigos que você acompanha</a></li>
                        <li class="wb-font-small"><a href="">10 Amigos que te acompanham</a></li>
                        <li class="wb-font-small"><a href="">10 Filtros</a></li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="grid_12">
            <div class="wb-box-with-shadow popular-content">
                <h3>Sua Conta</h3>
                <div class="tabbable"> <!-- Only required for left/right tabs -->
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Perfil</a></li>
                        <li><a href="#tab2" data-toggle="tab">Alterar Senha</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <h4>Seu Perfil</h4>
                            <wb:message messageBean="${message}" />
                            <form:form action="${pageContext.request.contextPath}/users/edit" method="put" id="login-form" commandName="user">
                                <form:hidden path="id" />
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Nome de Usuário</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="userName" cssClass="error" />
                                        <input class="disabled" type="text" name="userName" value="${user.userName}">
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Nome</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="name" cssClass="error"/>
                                        <input class=""type="text" name="name" value="${user.name}">
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Sobrenome</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="lastName" cssClass="error" />
                                        <input class=""type="text" name="lastName" value="${user.lastName}">
                                    </div>
                                </div>

                                <div class="field-block">
                                    <div class="field-title">
                                        <label>E-mail</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="email" cssClass="error"/>
                                        <input class=""type="text" name="email" value="${user.email}">
                                    </div>
                                </div>


                                <div class="controls">
                                    <div class="control">
                                        <button id="user-account-save" class="btn btn-primary" type="submit">Salvar</button>
                                        <button class="btn">Cancelar</button>
                                    </div>
                                    <span class="wb-font-medium"></span>
                                </div>
                            </form:form>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <h4>Altere sua Senha</h4>
                            <form:form action="${pageContext.request.contextPath}/users/edit/password" id="login-form" commandName="userChangePasswordForm" method="put">
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Senha Antiga</label>
                                    </div>
                                    <div class="field-input">
                                        <input class=""type="password" name="oldPassword">
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Nova Senha</label>
                                    </div>
                                    <div class="field-input">
                                        <input class=""type="password" name="newPassword">
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Nova Senha novamente</label>
                                    </div>
                                    <div class="field-input">
                                        <input class=""type="password" name="passwordVerification">
                                    </div>
                                </div>

                                <div class="controls">
                                    <div class="control">
                                        <!-- <input id="button-register" class="wb-border-radius-all" type="button" value="Salvar"> -->
                                        <!-- <input id="button-register" class="wb-border-radius-all" type="button" value="Cancelar"> -->
                                        <button class="btn btn-primary" type="submit">Salvar</button>
                                        <button class="btn" type="reset">Cancelar</button>
                                    </div>
                                    <span class="wb-font-medium"></span>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </body>
</html>
