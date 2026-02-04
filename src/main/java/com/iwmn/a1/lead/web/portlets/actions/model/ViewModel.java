package com.iwmn.a1.lead.web.portlets.actions.model;

import com.iwmn.a1.lead.web.portlets.Constants;
import com.iwmn.a1.lead.web.portlets.utils.RequestUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private String pageJsp;

    private String moduleJsp = Constants.Modules.DASHBOARD;

    Map<String, Object> attributes = new HashMap<>();

    public ViewModel() {
    }

    public ViewModel(String pageJsp, String moduleJsp) {
        this.pageJsp = pageJsp;
        this.moduleJsp = moduleJsp;
    }

    public ViewModel(String pageJsp) {
        this.pageJsp = pageJsp;
    }

    public void setPageJsp(String pageJsp) {
        this.pageJsp = pageJsp;
    }

    public void setModuleJsp(String moduleJsp) {
        this.moduleJsp = moduleJsp;
    }

    public void setAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }

    public void setError(String errorMessage) {
        this.attributes.put(Constants.Attributes.ERROR, errorMessage);
        System.err.println("Showing error: " + errorMessage);
    }

    public void setStatus(String statusMessage) {
        this.attributes.put(Constants.Attributes.STATUS, statusMessage);
    }

    public void setScript(String scriptTag) {
        this.attributes.put(Constants.Attributes.SCRIPT, scriptTag);
    }

    public String paramAsAttribute(PortletRequest request, String paramName) {
        String value = RequestUtil.getParam(request, paramName);
        this.setAttribute(paramName, value);
        return value;
    }

    public String apply(PortletRequest request) {
        if (this.pageJsp == null) {
            throw new NullPointerException("The render page must not be null!");
        }
        this.attributes.put(Constants.Attributes.INCLUDE_MODULE, this.moduleJsp);
        this.attributes.forEach(request::setAttribute);
        return this.pageJsp;
    }

    public void apply(ActionRequest request, ActionResponse response) {
        if (this.pageJsp == null) {
            throw new NullPointerException("The render page must not be null!");
        }
        this.attributes.forEach(request::setAttribute);
        this.attributes.put(Constants.Attributes.INCLUDE_MODULE, this.moduleJsp);
        response.setRenderParameter(Constants.Attributes.JSP_PAGE, this.pageJsp);
    }
}
