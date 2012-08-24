<!DOCTYPE html>
<html lang="en">
    <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>
    <head>
        <meta charset="utf-8" />
        <title><decorator:title /></title>
        <!-- <link href='http://fonts.googleapis.com/css?family=Patua+One' rel='stylesheet' type='text/css'> -->
        <!-- <link href='http://fonts.googleapis.com/css?family=Lato|Metrophobic' rel='stylesheet' type='text/css'> -->

        <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/reset.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/text.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/960.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/bootstrap.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/main.css" />
        
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.json-2.2.js"></script>
        <decorator:head />
    </head>

    <body class="webbook">
        <!-- CabeÃ§alho -->
        <header>
            <nav class="container_16">
                <a class="logo wb-left-float" href="">Webbook</a>
                <form id="searchbox" class="wb-left-float" action="">
                    <input id="search" class="wb-left-float" type="text">
                    <input id="submit" type="submit" class="wb-left-float" value="Pesquisar">
                </form>
                <sec:authorize access="isAuthenticated()">
                    <ul class="user-account wb-right-float">
                        <li>
                            <a href="#" class="avatar">
                                <wb:gravatar email="${userInstance.email}" />
                            </a>
                        </li>
                        <li class="dropdown" id="dropdow-user">
                            <a href="#dropdow-user" class="dropdown-toggle" data-toggle="dropdown">
                                @<sec:authentication property="principal.username" />
                                <span class="caret"></span>
                            </a>

                            <ul class="dropdown-menu" id="user-dropdown">
                                <li class="dropdown-caret">
                                    <span class="caret-outer"></span>
                                    <span class="caret-inner"></span>
                                </li>
                                <li><a href="<%= request.getContextPath()%>/bookmarks"><i class="icon-bookmark"></i>Meus favoritos</a></li>
                                <li><a href="#">Meus filtros</a></li>
                                <li><a href="#">Meus amigos</a></li>
                                <li class="dropdown-divider"></li>
                                <li><a href="${pageContext.request.contextPath}/users/account/profile"><i class="icon-user"></i>Meu Perfil</a></li>
                                <li class="dropdown-divider"></li>
                                <li><a href="${pageContext.request.contextPath}/logout" class="">Sair</a></li>
                            </ul>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/logout" class="">Sair</a></li>
                    </ul>    
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <ul class="wb-right-float">
                        <li><a href="login" id="button-login" class="wb-border-radius-all">Entrar</a></li>
                        <li><a href="users/register" id="button-sign" class="wb-border-radius-all">Cadastre-se</a></li>
                    </ul>
                </sec:authorize>

            </nav>
        </header>
        <!-- Conteúdo-->
        <section id="content" class="container_12">
            <decorator:body />
        </section>

        <!--Rodapé-->
        <footer>
            <ul>
                <li><a href="#">Sobre</a></li>

                <li><a href="#">Ferramentas</a></li>

                <li><a href="#" target="_blank">Blog</a></li>

                <li><a href="#">Ajuda</a></li>

                <li><a href="#">Desenvolvedores</a></li>

                <li><a href="#">Feeds</a></li>

                <li><a href="#">Termos de uso</a></li>

                <li><a href="#">Política de privacidade</a></li>

            </ul>
        </footer>

    </body>


</html>

