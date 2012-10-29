<%-- 
    Document   : dashboard
    Created on : 28/10/2012, 15:09:49
    Author     : maykoone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="grid_12">
            <section class="wb-box-with-shadow popular-content">
                <h3>Tags</h3>
                <p class="muted">Veja as tags mais populares no Webbook e descubra novos favoritos</p>
                <hr>
                <div class="tag-cloud"></div>
            </section>
        </div>
        <div class="grid_12">
            <section class="wb-box-with-shadow popular-content">
                <h3>Favoritos</h3>
                <div class="tabbable">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Populares</a></li>
                        <li><a href="#tab2" data-toggle="tab">Recentes</a></li>
                        <li><a href="#tab2" data-toggle="tab">Seguidores</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <p>I'm in Section 1.</p>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <p>Howdy, I'm in Section 2.</p>
                        </div>
                        <div class="tab-pane" id="tab3">
                            <p>Howdy, I'm in Section 2.</p>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
