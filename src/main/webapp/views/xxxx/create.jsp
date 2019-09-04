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
<security:authorize access="hasRole('ADMIN')">

    <spring:message code="actor.firstMessage" />

    <form:form id="xxxx" action="xxxx/administrator/save.do" modelAttribute="xxxx">

        <form:hidden path="id" />
        <form:hidden path="conference"/>

        <jstl:out value="${conference}"/>

        <br>
        <fieldset>

            <legend><spring:message code="xxxx.data" /></legend>

            <acme:textboxbsa code="xxxx.body" path="body"/>
            <acme:textboxbsa code="xxxx.cozitah1" path="cozitah1"/>
            <acme:textboxbsa code="xxxx.cozitah2" path="cozitah2"/>

        </fieldset>

        <br>
        <br>

        <acme:submit name="draft" code="button.draft"/>
        <acme:submit name="final" code="button.final"/>
        <acme:cancel code="button.cancel" url="/"/>

    </form:form>

</security:authorize>
</body>
</html>
