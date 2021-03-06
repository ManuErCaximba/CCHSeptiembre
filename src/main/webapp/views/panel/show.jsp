<%--
  Created by IntelliJ IDEA.
  User: MrGaRRos
  Date: 24/08/2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

</head>
<body>
<fieldset>
    <legend><b><spring:message code="presentation.data"/></b></legend>

    <p><acme:showtext code="presentation.title" value="${panel.title}" fieldset="false"/></p>
    <p><acme:showtext code="presentation.startMoment" value="${panel.startMoment}" fieldset="false"/></p>
    <p><acme:showtext code="presentation.durationInMin" value="${panel.duration}" fieldset="false"/></p>
    <p><acme:showtext code="presentation.room" value="${panel.room}" fieldset="false"/></p>
    <p><acme:showtext code="presentation.summary" value="${panel.summary}" fieldset="false"/></p>

    <b><spring:message code="presentation.attachments"/>:</b>
    <br />
    <jstl:forEach items="${panel.attachments}" var="attachment">
        <a href="${attachment.link}"><jstl:out value="${attachment.link}"/></a> <br>
    </jstl:forEach>
</fieldset>
<fieldset>
    <p><acme:showtext code="presentation.speakerName" value="${panel.speakerName}" fieldset="false"/></p>
</fieldset>
<br>
<acme:cancel code="button.cancel" url="/"/>
</body>
</html>
