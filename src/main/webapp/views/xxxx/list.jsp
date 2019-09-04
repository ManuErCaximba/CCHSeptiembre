<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
             pageEncoding="ISO-8859-1" %>

    <%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <%@taglib prefix="display" uri="http://displaytag.sf.net" %>
    <%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="jtsl" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <style type="text/css">
        .GREEN{
            background-color: Indigo;
        }
        .ORANGE{
            background-color: DarkSlateGray;
        }
        .RED{
            background-color: PapayaWhip;
        }
    </style>

</head>
<body>


    <display:table name="xxxxs" id="row" requestURI="${requestURI}"
                   pagesize="5" class="displaytag">

        <jstl:choose>
            <jstl:when test="${row.moment!=null}">
                <jstl:choose>
                    <jstl:when test="${row.moment > haceUnMes }">
                        <jstl:set var="css" value="GREEN"/>
                    </jstl:when>
                    <jstl:when test="${row.moment < haceUnMes && row.moment > haceDosMeses}">
                        <jstl:set var="css" value="ORANGE"/>
                    </jstl:when>
                    <jstl:otherwise>
                        <jstl:set var="css" value="RED" />
                    </jstl:otherwise>
                </jstl:choose>
            </jstl:when>
            <jstl:otherwise>
                <jstl:set var="css" value="null" />
            </jstl:otherwise>
        </jstl:choose>

        <spring:message code="xxxx.ticker" var="ticker"/>
        <display:column title="${ticker}" class="${css}">
            <jstl:out value="${row.ticker}"/>
        </display:column>

        <spring:message code="xxxx.moment" var="moment"/>
        <jstl:choose>
            <jstl:when test="${lang == 'es'}">
                <display:column class="${css}" title="${moment}">
                    <fmt:formatDate value="${row.moment}" pattern="dd-MM-yy hh:mm"/>
                </display:column>
            </jstl:when>
            <jstl:otherwise>
                <display:column class="${css}" title="${moment}">
                    <fmt:formatDate value="${row.moment}" pattern="yy/MM/dd hh:mm"/>
                </display:column>
            </jstl:otherwise>
        </jstl:choose>

        <display:column class="${css}">
            <acme:cancel url="xxxx/show.do?xxxxId=${row.id}" code="button.show"/>
        </display:column>

        <security:authorize access="hasRole('ADMIN')">
            <display:column class="${css}">
                <jstl:if test="${row.isFinal == false}">
                    <acme:cancel url="xxxx/administrator/edit.do?xxxxId=${row.id}" code="button.edit"/>
                </jstl:if>
            </display:column>
        </security:authorize>

        <security:authorize access="hasRole('ADMIN')">
            <display:column class="${css}">
                <jstl:if test="${row.isFinal == false}">
                    <acme:cancel url="xxxx/administrator/delete.do?xxxxId=${row.id}" code="button.delete"/>
                </jstl:if>
            </display:column>
        </security:authorize>

    </display:table>

    <security:authorize access="hasRole('ADMIN')">
        <div>
            <acme:cancel url="xxxx/administrator/create.do?conferenceId=${conferenceId}" code="button.create"/>
        </div>
    </security:authorize>

</body>
</html>
