<%-- 
    Document   : tagLink
    Created on : 16/11/2012, 20:39:33
    Author     : maykoone
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="tag" required="true"%>
<%@attribute name="frequency" required="false"%>

<%-- any content can be specified here e.g.: --%>
<a href='${pageContext.request.contextPath}/bookmarks/tag/${tag.replaceAll("[^a-zA-Z 0-9,ã,á,à,â,ê,í,ú,ù,õ,ô,é,ü]+","-").replaceAll("\\s", "-")}' rel="${frequency}"><span class="tag">#${tag}</span></a>