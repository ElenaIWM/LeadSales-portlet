package com.iwmn.a1.lead.web.portlets.admin;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.NetflixLeadModel;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.repository.jpa.NetflixLeadRepository;
import com.iwmn.a1.lead.repository.jpa.SchoolLeadRepository;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

public class NetflixLeadAdmin extends MVCPortlet {
    NetflixLeadRepository repository;

    public NetflixLeadAdmin(){
        this.repository = ApplicationContextHolder.getBean(NetflixLeadRepository.class);
    }

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        List<NetflixLeadModel> netflixLeadModels = repository.findAll();
        renderRequest.setAttribute("netflixLeads", netflixLeadModels);
        super.render(renderRequest, renderResponse);
    }
}
