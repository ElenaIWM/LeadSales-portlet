<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${error!=null}">
	<div class="position-relative">
		<div class="alert alert-danger alert-dismissible fade show top-right" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<liferay-ui:message key="${error}"/>
		</div>
	</div>
</c:if>
<c:if test="${status!=null}">
	<c:if test="${not empty showSurvey and showSurvey eq 'showSurvey'}">
		<script id="customScript" type="module" src="/o/LeadSales-portlet/js/survey-popup-widget.js?address=${email}&Mobile_Number=${msisdn}"></script>
	</c:if>
	<div class="position-relative">
		<div class="alert alert-primary alert-dismissible fade show top-right" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<c:choose>
				<c:when test="${statusArg!=null}">
					<liferay-ui:message key="${status}" arguments="${statusArg}"/>
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="${status}"/>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</c:if>

