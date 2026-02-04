<%@ include file="/admin-init.jsp"%>
<%@ page import="javax.portlet.RenderRequest"%>

<%@ page import="com.iwmn.a1.lead.web.portlets.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>

<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="com.iwmn.a1.lead.web.portlets.Constants" %>

<liferay-theme:defineObjects />
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%  
String leadCategory_cfg =  GetterUtil.getString(portletPreferences.getValue("leadCategory", Constants.LEAD_SALES));
String formDescMK =  GetterUtil.getString(portletPreferences.getValue("formDescMK", ""));
String formDescAL =  GetterUtil.getString(portletPreferences.getValue("formDescAL", ""));
String formDescEN =  GetterUtil.getString(portletPreferences.getValue("formDescEN", ""));

String leadEmail = GetterUtil.getString(portletPreferences.getValue("leadEmail", ""));

String userType = GetterUtil.getString(portletPreferences.getValue("userType", "private"));
String installationAddress = GetterUtil.getString(portletPreferences.getValue("installationAddress", "false"));
String showSurvey = GetterUtil.getString(portletPreferences.getValue("showSurvey", "false"));
String agreePersonalData = GetterUtil.getString(portletPreferences.getValue("agreePersonalData", "false"));

%>

<aui:form action="<%= configurationURL %>" method="post" name="fm" style="margin:30px;">
    <aui:input name="<%= com.liferay.portal.kernel.util.Constants.CMD %>" type="hidden" value="<%= com.liferay.portal.kernel.util.Constants.UPDATE %>" />
    <h3>Choose User Type</h3>
    <aui:input name="preferences--userType--" type="radio" value="private" checked="<%=userType.equals(Constants.USER_TYPE_PRIVATE) %>" label="Private User"/>
    <aui:input name="preferences--userType--" type="radio" value="business" checked="<%=userType.equals(Constants.USER_TYPE_BUSINESS) %>" label="Business User"/>
    
    <h3>Show survey?</h3>
    <aui:input name="preferences--showSurvey--" type="checkbox" value="showSurvey" checked="<%=showSurvey.equals(Constants.SHOW_SURVEY) %>" label="Show survey"/>
    
     <h3>Show personal data?</h3>
    <aui:input name="preferences--agreePersonalData--" type="checkbox" value="agreePersonalData" checked="<%=agreePersonalData.equals(Constants.SHOW_PERSONAL_DATA) %>" label="Show agree Personal Data"/>
    
   
    <h3>Instalation Address?</h3>
    <aui:input name="preferences--installationAddress--" type="checkbox" value="installationAddress" checked="<%=installationAddress.equals(Constants.INSTALLATION_ADDRESS) %>" label="Instalation Address"/>
    
    <h3>Choose Lead Type</h3>
    <aui:input name="preferences--leadCategory--" type="radio" value="1" checked="<%=leadCategory_cfg.equals(Constants.LEAD_SALES) %>" label="Lead-sales"/>
    <aui:input name="preferences--leadCategory--" type="radio" value="2" checked="<%=leadCategory_cfg.equals(Constants.LEAD_CARE) %>" label="Lead-care"/>
    <aui:input name="preferences--leadCategory--" type="radio" value="3" checked="<%=leadCategory_cfg.equals(Constants.LEAD_OTHER) %>" label="Lead-other"/>
	<aui:input name="preferences--leadEmail--" type="text" size="50" label="Lead email (other)" value="<%=leadEmail %>"/>         

	<h3>Choose Lead Form text (Lead form only)</h3>
	<aui:input name="preferences--formDescMK--" type="textarea" value="<%=formDescMK %>" rows="15" cols="80" label="Lead-form-text-MK"/>
	<aui:input name="preferences--formDescAL--" type="textarea" value="<%=formDescAL %>" rows="15" cols="80" label="Lead-form-text-AL"/>
	<aui:input name="preferences--formDescEN--" type="textarea" value="<%=formDescEN %>" rows="15" cols="80" label="Lead-form-text-EN"/>

    <aui:button-row>
       <aui:button style="margin-top:20px" type="submit"  />
    </aui:button-row>
</aui:form>

<style>
    .navbar{
        display: none;
    }
</style>