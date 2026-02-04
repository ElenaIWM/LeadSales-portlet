package com.iwmn.a1.lead.web.portlets.api;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

public interface AsyncAction {

    String process(ResourceRequest resourceRequest,
                   ResourceResponse resourceResponse) throws IOException,
            PortletException;

}
