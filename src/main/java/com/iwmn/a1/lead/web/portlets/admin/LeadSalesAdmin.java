package com.iwmn.a1.lead.web.portlets.admin;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.repository.jpa.LeadSalesRepository;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

public class LeadSalesAdmin extends MVCPortlet {

    LeadSalesRepository repository;

    public LeadSalesAdmin(){
        this.repository = ApplicationContextHolder.getBean(LeadSalesRepository.class);
    }

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        List<LeadSalesModel> leadSalesModels = repository.findAll();
        renderRequest.setAttribute("leadSales", leadSalesModels);
        super.render(renderRequest, renderResponse);
    }
}
