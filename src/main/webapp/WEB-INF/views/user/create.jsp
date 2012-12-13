<%-- 
    Document   : create
    Created on : 12/08/2012, 15:37:49
    Author     : maykoone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastre-se</title>
        <!-- <link href='http://fonts.googleapis.com/css?family=Patua+One' rel='stylesheet' type='text/css'> -->
        <!-- <link href='http://fonts.googleapis.com/css?family=Lato|Metrophobic' rel='stylesheet' type='text/css'> -->


    </head>

    <body>

        <!-- Conteúdo-->
        <div class="grid_4">
            <section class="wb-box-with-shadow popular-content register-presentation">
                <h4>Organize a sua Web</h4>
                <p class="wb-font-medium">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor."</p>
                <a href="" class="btn btn-small">Saiba Mais >></a>
            </section>
            <section class="wb-box-with-shadow popular-content register-presentation">
                <h4>Encontre conteúdo do seu Interesse na Internet</h4>
                <p class="wb-font-medium">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor."</p>
                <a href="" class="btn btn-small">Saiba Mais >></a>
            </section>
            <section class="wb-box-with-shadow popular-content register-presentation">
                <h4>Compartilhe</h4>
                <p class="wb-font-medium">"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor."</p>
                <a href="" class="btn btn-small">Saiba Mais >></a>
            </section>
        </div>
        <div class="grid_8" >
            <div class="wb-box-with-shadow popular-content" id="login-form-container">
                <h4>Descubra, Colecione e Compartilhe novos conteúdos da Web</h4>
                <form:form action="create_account" commandName="user" id="login-form" method="post">

                    <div class="field-block">
                        <div class="field-title">
                            <label>Nome de Usuário</label>
                        </div>
                        <div class="field-input">
                            <form:errors path="userName" cssClass="error" />
                            <input class="" type="text" name="userName" />
                        </div>
                    </div>
                    <div class="field-block">
                        <div class="field-title">
                            <label>E-mail</label>
                        </div>
                        <div class="field-input">
                            <form:errors path="email" cssClass="error" />
                            <input class=""type="text" name="email" />
                        </div>
                    </div>
                    <div class="field-block">
                        <div class="field-title">
                            <label>Senha</label>
                        </div>
                        <div class="field-input">
                            <form:errors path="password" cssClass="error"/>
                            <input class=""type="password" name="password" />
                        </div>
                    </div>

                    <!--                    <div class="field-block">
                                            <div class="field-title">
                                                <label>Senha novamente</label>
                                            </div>
                                            <div class="field-input">
                                                <input class="" type="password">
                                            </div>
                                        </div>-->
                    <span class="wb-font-medium"><a href="">Leia o Termo de Uso antes de se Cadastrar</a></span>
                    <div class="field-block">

                        <label class="wb-font-medium checkbox">
                            <input class="" type="checkbox">Concordo com o Termo de Uso
                        </label>
                    </div>
                    <div class="controls">
                        <div class="control">
                            <input id="button-register" class="wb-border-radius-all" type="submit" value="Cadastre-se">
                        </div>
                        <span class="wb-font-medium"></span>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="clear"></div>

    </body>


</html>


