<%@include file="/admin-init.jsp"%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Collections"%>

<%!
	private List<String> errors=new ArrayList<String>();
	private List<String> success=new ArrayList<String>();


	public void getAllErrors(HttpServletRequest request) {
		HttpServletRequest origRequest = PortalUtil.getOriginalServletRequest(request);
		errors.clear();
		success.clear();
		Set<String> paramsSet=new HashSet<String>();
		paramsSet.addAll(request.getParameterMap().keySet());
		paramsSet.addAll(origRequest.getParameterMap().keySet());
		
		paramsSet.addAll(Collections.list(request.getAttributeNames()));
		
		for(String key: paramsSet){
			if(key.contains("error")) {
				errors.add(key);
			}
			
			if(key.contains("success")) {
				success.add(key);
			}
		}
	}
%>

<%
	getAllErrors(request);
	if(!errors.isEmpty()) {
%>
		<script type="text/javascript">
			$( document ).ready(function() {
				$("#internetAvailabilityContent").hide();
				$("#errorMsg").show();
				$('html, body').animate({
			        scrollTop: parseInt($("#internetAvailabilityWrapper").offset().top-150)
			    }, 2000);
			});			
		</script>
<%
	}

	if(!success.isEmpty()) {
%>
		<script type="text/javascript">
			$( document ).ready(function() {
				$("#internetAvailabilityContent").hide();
				$("#successMsg").show();
				$('html, body').animate({
			        scrollTop: parseInt($("#internetAvailabilityWrapper").offset().top-150)
			    }, 2000);
			});			
		</script>		
<%
	}
%>