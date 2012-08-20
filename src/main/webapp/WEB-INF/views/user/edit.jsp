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
                    <img src="http://icons.iconarchive.com/icons/pixelmixer/basic/64/user-icon.png" alt="avatar" />
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
                            <h3>Seu Perfil</h3>
                            <c:if test="${message != null}">
                                <div class="alert">
                                    <button class="close" data-dismiss="alert">×</button>
                                    <p>${message.message}</p>
                                </div>
                            </c:if>
                            <form:form action="${pageContext.request.contextPath}/users/edit" method="put" id="login-form" commandName="userInstance">
                                <form:hidden path="id" />
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Nome de Usuário</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="userName" />
                                        <input class="disabled" type="text" name="userName" value="${userInstance.userName}">
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Nome</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="name" />
                                        <input class=""type="text" name="name" value="${userInstance.name}">
                                    </div>
                                </div>
                                <div class="field-block">
                                    <div class="field-title">
                                        <label>Sobrenome</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="lastName" />
                                        <input class=""type="text" name="lastName" value="${userInstance.lastName}">
                                    </div>
                                </div>

                                <div class="field-block">
                                    <div class="field-title">
                                        <label>E-mail</label>
                                    </div>
                                    <div class="field-input">
                                        <form:errors path="email" />
                                        <input class=""type="text" name="email" value="${userInstance.email}">
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
                            <h3>Altere sua Senha</h3>
                            <form:form action="${pageContext.request.contextPath}/users/edit/password" id="login-form" commandName="userChangePassword" method="put">
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
