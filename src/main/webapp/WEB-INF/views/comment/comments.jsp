<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="/WEB-INF/tags/webbook.tld" prefix="wb" %>

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
                <textarea rows="2" name="text"></textarea>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn btn-primary btn-small" data-loading-text="Carregando...">Comentar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <hr />
    <div class="row-fluid">
        <div class="span12">
            <ul id="comments-list">
                <h5>${fn:length(comments)} Comentários </h5>
                <c:forEach var="comment" items="${comments}">
                    <li class="comment-item">
                        <div class="comment-content">
                            <a href="" class="avatar">
                                <wb:gravatar email="${comment.user.email}" />
                            </a>   
                            <div class="comment-text">
                                <h6>Comentário por <a href="${pageContext.request.contextPath}/bookmarks/${comment.user.userName}">${comment.user.userName}</a></h6>
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
        
        
        $("#comment-form").submit(function() {
            var comment = $(this).serialize();
            $.post("${pageContext.request.contextPath}/ajax/comments", comment, function(data){
                $('ul#comments-list').prepend(data).slideDown("slow");
            });
            return false;
        });
    })
</script>
