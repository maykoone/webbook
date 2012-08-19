<%-- 
    Document   : login
    Created on : 11/08/2012, 17:49:13
    Author     : maykoone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Login</title>

    </head>

    <body>
        <div class="grid_8 prefix_2 suffix_2" >
            <div class="wb-box-with-shadow popular-content" id="login-form-container">
                <h3 class="wb-font-x-big">Faça seu Login</h3>
                <p class="wb-font-small">Ainda não tem uma conta? <a href="">Cadastre-se</a></p>
                <form action="<c:url value='j_spring_security_check' />" method="post" id="login-form">
                    <div class="field-block">
                        <div class="field-title">
                            <label>Nome de usuário</label>
                        </div>
                        <div class="field-input">
                            <input class=""type="text" name="j_username">
                        </div>
                    </div>

                    <div class="field-block">
                        <div class="field-title">
                            <label>Senha</label>
                        </div>
                        <div class="field-input">
                            <input class="" type="password" name="j_password">
                        </div>
                        <span class="wb-font-small"><a href="">Esqueceu a Senha?</a></span>
                    </div>

                    <div class="field-block">
                        <input class="" type="checkbox" name="_spring_security_remember_me">
                        <label class="wb-font-small">Manter conectado</label>
                    </div>
                    <div class="controls">
                        <div class="control">
                            <input id="button-register" class="wb-border-radius-all" type="submit" value="Entrar">
                        </div>
                        <span class="wb-font-medium"></span>
                    </div>
                </form>
            </div>
        </div>
        <div class="clear"></div>


    </body>


</html>


