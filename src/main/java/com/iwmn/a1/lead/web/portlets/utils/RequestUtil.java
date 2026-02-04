package com.iwmn.a1.lead.web.portlets.utils;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getParam(PortletRequest req, String name) {
        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(req);
        HttpServletRequest origRequest = PortalUtil.getOriginalServletRequest(httpRequest);
        String val = origRequest.getParameter(name);
        if (val == null) {
            UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(req);
            val = uploadRequest.getParameter(name);
        }
        if (val == null) {
            val = (String) req.getAttribute(name);
        }
        return val;
    }

    public static Integer getIntegerParam(PortletRequest req, String name, Integer def) {
        String param = getParam(req, name);
        try {
            return Integer.parseInt(param);
        } catch (Exception ex) {
            return def;
        }
    }


    public static Double getDoubleParam(PortletRequest req, String name, Double def) {
        String param = getParam(req, name);
        try {
            return Double.parseDouble(param);
        } catch (Exception ex) {
            return def;
        }
    }

    public static boolean getBooleanParam(PortletRequest req, String name, Boolean def) {
        String param = getParam(req, name);
        try {
            return Boolean.parseBoolean(param);
        } catch (Exception ex) {
            return def;
        }
    }

}
