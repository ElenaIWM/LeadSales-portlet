<%@ include file="/admin-init.jsp"%>
<%@ page import="javax.portlet.RenderRequest"%>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>

<%@ page import="javax.portlet.PortletPreferences" %>

<liferay-theme:defineObjects />
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />


<%  

String backgroundImagePath =  GetterUtil.getString(portletPreferences.getValue("backgroundImagePath", ""));

//MK
String formTitleMK =  GetterUtil.getString(portletPreferences.getValue("formTitleMK", ""));
String formDescriptionMK =  GetterUtil.getString(portletPreferences.getValue("formDescriptionMK", ""));
String buttonCityLabelMK = GetterUtil.getString(portletPreferences.getValue("buttonCityLabelMK", ""));
String buttonMsisdnLabelMK = GetterUtil.getString(portletPreferences.getValue("buttonMsisdnLabelMK", ""));
String buttonSubmitNameMK = GetterUtil.getString(portletPreferences.getValue("buttonSubmitNameMK", ""));

//EN
String formTitleEN =  GetterUtil.getString(portletPreferences.getValue("formTitleEN", ""));
String formDescriptionEN =  GetterUtil.getString(portletPreferences.getValue("formDescriptionEN", ""));
String buttonCityLabelEN = GetterUtil.getString(portletPreferences.getValue("buttonCityLabelEN", ""));
String buttonMsisdnLabelEN = GetterUtil.getString(portletPreferences.getValue("buttonMsisdnLabelEN", ""));
String buttonSubmitNameEN = GetterUtil.getString(portletPreferences.getValue("buttonSubmitNameEN", ""));

//AL
String formTitleAL =  GetterUtil.getString(portletPreferences.getValue("formTitleAL", ""));
String formDescriptionAL =  GetterUtil.getString(portletPreferences.getValue("formDescriptionAL", ""));
String buttonCityLabelAL = GetterUtil.getString(portletPreferences.getValue("buttonCityLabelAL", ""));
String buttonMsisdnLabelAL = GetterUtil.getString(portletPreferences.getValue("buttonMsisdnLabelAL", ""));
String buttonSubmitNameAL = GetterUtil.getString(portletPreferences.getValue("buttonSubmitNameAL", ""));

%>


<aui:form action="<%= configurationURL %>" method="post" name="fm" style="margin:200px 0 0 0;padding-bottom: 100px;">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <h5>Background Image Path</h5>
    <aui:input name="preferences--backgroundImagePath--" type="text" size="50" value="<%=backgroundImagePath %>" label=""/>
    
    <h5>Form Title MK</h5>
    <aui:input name="preferences--formTitleMK--" type="textarea" value="<%=formTitleMK %>" rows="5" cols="80" label=""/>
    
    <h5>Form Title EN</h5>
    <aui:input name="preferences--formTitleEN--" type="textarea" value="<%=formTitleEN %>" rows="5" cols="80" label=""/>
    
    <h5>Form Title AL</h5>
    <aui:input name="preferences--formTitleAL--" type="textarea" value="<%=formTitleAL %>" rows="5" cols="80" label=""/>
    
    <h5>Form Description MK</h5>
    <aui:input name="preferences--formDescriptionMK--" type="textarea" value="<%=formDescriptionMK %>" rows="5" cols="80" label=""/>
    
    <h5>Form Description EN</h5>
    <aui:input name="preferences--formDescriptionEN--" type="textarea" value="<%=formDescriptionEN %>" rows="5" cols="80" label=""/>
    
    <h5>Form Description AL</h5>
    <aui:input name="preferences--formDescriptionAL--" type="textarea" value="<%=formDescriptionAL %>" rows="5" cols="80" label=""/>
    
    <h5>Input City Label MK</h5>
    <aui:input name="preferences--buttonCityLabelMK--" type="text" size="50" value="<%=buttonCityLabelMK %>" label=""/>
    
    <h5>Input City Label EN</h5>
    <aui:input name="preferences--buttonCityLabelEN--" type="text" size="50" value="<%=buttonCityLabelEN %>" label=""/>
    
    <h5>Input City Label AL</h5>
    <aui:input name="preferences--buttonCityLabelAL--" type="text" size="50" value="<%=buttonCityLabelAL %>" label=""/>
    
    <h5>Input Msisdn Label MK</h5>
    <aui:input name="preferences--buttonMsisdnLabelMK--" type="text" size="50" value="<%=buttonMsisdnLabelMK %>" label=""/>
    
    <h5>Input Msisdn Label EN</h5>
    <aui:input name="preferences--buttonMsisdnLabelEN--" type="text" size="50" value="<%=buttonMsisdnLabelEN %>" label=""/>
    
    <h5>Input Msisdn Label AL</h5>
    <aui:input name="preferences--buttonMsisdnLabelAL--" type="text" size="50" value="<%=buttonMsisdnLabelAL %>" label=""/>
    
    <h5>Button Submit Name MK</h5>
    <aui:input name="preferences--buttonSubmitNameMK--" type="text" size="50" value="<%=buttonSubmitNameMK %>" label=""/>
    
    <h5>Button Submit Name EN</h5>
    <aui:input name="preferences--buttonSubmitNameEN--" type="text" size="50" value="<%=buttonSubmitNameEN %>" label=""/>
    
    <h5>Button Submit Name AL</h5>
    <aui:input name="preferences--buttonSubmitNameAL--" type="text" size="50" value="<%=buttonSubmitNameAL %>" label=""/>
    
    <aui:button-row>
       <aui:button style="margin-top:20px" type="submit"  />
    </aui:button-row>
</aui:form>