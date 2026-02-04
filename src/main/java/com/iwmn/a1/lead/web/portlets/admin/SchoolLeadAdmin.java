package com.iwmn.a1.lead.web.portlets.admin;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.repository.jpa.SchoolLeadRepository;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

public class SchoolLeadAdmin extends MVCPortlet {

    SchoolLeadRepository repository;

    public SchoolLeadAdmin(){
        this.repository = ApplicationContextHolder.getBean(SchoolLeadRepository.class);
    }

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        List<SchoolLeadModel> schoolLeadModels = repository.findAll();
        renderRequest.setAttribute("schoolLeads", schoolLeadModels);
        super.render(renderRequest, renderResponse);
    }
}
