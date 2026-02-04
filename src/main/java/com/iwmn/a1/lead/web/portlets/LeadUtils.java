package com.iwmn.a1.lead.web.portlets;

import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public class LeadUtils {
    public static String getIpAddress(PortletRequest request) {
        HttpServletRequest req = PortalUtil
                .getHttpServletRequest(request);
        String remAddr = req.getHeader("HTTP-X-ASMP-FORWARDED-FOR");
        if (remAddr == null) {
            remAddr = req.getRemoteAddr();
        }
        return remAddr;
    }
}
