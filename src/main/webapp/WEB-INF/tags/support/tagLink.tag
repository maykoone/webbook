<%-- 
    Document   : tagLink
    Created on : 16/11/2012, 20:39:33
    Author     : maykoone
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tags/functions.tld" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="tag" required="true"%>
<%@attribute name="frequency" required="false"%>

<%-- any content can be specified here e.g.: --%>
<a href="/bookmarks/tag/${util:urlEncode(tag)}" rel="${frequency}"><span class="tag">#${tag}</span></a>
