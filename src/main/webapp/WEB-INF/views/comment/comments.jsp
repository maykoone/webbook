<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span4">

            <div id="bookmark-summary">
                <h5>${bookmark.title}</h5>
                <p><a href="">${bookmark.url}<a/></p>
            </div> 
        </div>
        <div class="span8">
            <form action="#" id="comment-form" method="post">
                <input type="hidden" name="bookmark.id" value="${bookmark.id}"/>
                <!--<label>Comente</label>-->
                <textarea rows="2" name="text" name="text" id="comment-text" placeholder="Escreva aqui seu comentário"></textarea>
                <div class="control-group">
                    <div class="controls">
                        <span class="char-counter-comment">0</span>
                        <button type="submit" class="btn btn-primary btn-small" id="comment-button" data-loading-text="Carregando...">Comentar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <hr />
    <div class="row-fluid">
        <div class="span12">
            <h5>${fn:length(comments)} Comentários </h5>
            <ul id="comments-list">
                <c:forEach var="comment" items="${comments}">
                    <li class="comment-item">
                        <div class="comment-content">
                            <a href="" class="avatar">
                                <wb:gravatar email="${comment.user.email}" />
                            </a>   
                            <div class="comment-text">
                                <h6>Comentário por <a href="/bookmarks/${comment.user.userName}">${comment.user.userName}</a> em <fmt:formatDate value="${comment.creationDate}" pattern="d MMM, yyyy hh:mm"/></h6>
                                <p>${comment.text}</p>
                            </div>
                        </div>
                    </li> 
                </c:forEach>
            </ul>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function(){
        
        
        $("textarea#comment-text").keyup(function(){
            var counter = $(this).val().length;
            var buttonComment = $("#comment-button");
            if(counter > 140){
                buttonComment.attr("disabled", "disabled");
            } else {
                buttonComment.removeAttr("disabled");
            }
            
            $(".char-counter-comment").text(counter);
        });
        $("#comment-form").validate({
            rules: {
                text :{
                    required: true,
                    maxlength: 140
                }
            },
            messages: {
                text: {
                    required: "Escreva algum comentário antes de postar",
                    maxlength: "O comentário só pode ter 140 caracteres"
                }
            },
            submitHandler: function(form){
                var comment = $(form).serialize();
                $.post('/ajax/comments', comment, function(data){
                    $('ul#comments-list').prepend(data).slideDown("slow");
                });
            },
            errorPlacement: function(error, element) {
                error.insertBefore(element);
            }
        });
                
        
        //        $("#comment-form").submit(function() {
        //            var comment = $(this).serialize();
        //            $.post('/ajax/comments', comment, function(data){
        //                $('ul#comments-list').prepend(data).slideDown("slow");
        //            });
        //            return false;
        //        });
    })
</script>
