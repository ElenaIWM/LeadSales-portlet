package com.iwmn.a1.lead.web.portlets.api;

import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;

public interface Action {

    /**
     * @param request
     * @param response
     * @return the value of the JSP_PAGE render parameter
     * @throws IOException
     * @throws PortletException
     */
    ViewModel execute(PortletRequest request, PortletResponse response)
            throws IOException, PortletException;

}
