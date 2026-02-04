package com.iwmn.a1.lead.web.portlets;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.LanguageModel;
import com.iwmn.a1.lead.model.exceptions.RedirectViewModelException;
import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;
import com.iwmn.a1.lead.web.portlets.api.Action;
import com.iwmn.a1.lead.web.portlets.api.AsyncAction;
import com.iwmn.a1.lead.web.portlets.defaultActions.SchoolLeadDefaultAction;
import com.iwmn.a1.lead.web.portlets.utils.RequestUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SchoolLeadPortlet extends MVCPortlet {
    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
            throws IOException, PortletException {

        AsyncAction processor = ApplicationContextHolder.getBean(resourceRequest.getResourceID(), AsyncAction.class);

        if (processor != null) {
            String path = processor.process(resourceRequest, resourceResponse);
            if (path != null) {
                include(path, resourceRequest, resourceResponse);
                return;
            }
        }
        super.serveResource(resourceRequest, resourceResponse);

    }

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        HttpServletRequest request = PortalUtil
                .getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
        renderRequest.setAttribute("contextPath", renderRequest.getContextPath());
        renderRequest.setAttribute("currentPageUrl", request.getRequestURL().toString());
        renderRequest.setAttribute("currentDomain", request.getRequestURL().toString().replace(request.getRequestURI(), ""));
        renderRequest.setAttribute(Constants.Attributes.LOGIN_URL,
                ApplicationContextHolder.getEnvironment().getProperty("login.redirect.location"));

        String portletAction = RequestUtil.getParam(renderRequest, Constants.Params.PORTLET_ACTION);
        renderRequest.setAttribute("langHelper", new LanguageModel(LanguageUtil.getLanguageId(request)));
        renderRequest.setAttribute("lang", LanguageUtil.getLanguageId(renderRequest));

        try {
            ViewModel viewModel;
            Action action = obtainAction(renderRequest);
            viewModel = action.execute(renderRequest, renderResponse);
            if (viewModel != null) {
                String targetJsp = viewModel.apply(renderRequest);
                include(targetJsp, renderRequest, renderResponse);
            }
        } catch (RedirectViewModelException redirect) {
            HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(renderResponse);
            httpResponse.sendRedirect(redirect.getLocation());
        } catch (Exception e) {
            e.printStackTrace();
            renderRequest.setAttribute("exception", e);
            HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(renderResponse);
            httpResponse.sendRedirect(Constants.LOGIN_LOCATION);
        }
    }

    private Action obtainAction(RenderRequest renderRequest) {
        String requestedAction = RequestUtil.getParam(renderRequest, Constants.Params.PORTLET_ACTION);
        Action action = ApplicationContextHolder.getBean(requestedAction, Action.class);
        if (action == null) {
            return ApplicationContextHolder.getBean(SchoolLeadDefaultAction.ACTION_NAME, Action.class);
        } else {
            return action;
        }
    }
}
