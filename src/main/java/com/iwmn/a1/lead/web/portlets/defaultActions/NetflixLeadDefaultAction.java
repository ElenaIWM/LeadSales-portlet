package com.iwmn.a1.lead.web.portlets.defaultActions;

import com.iwmn.a1.lead.model.jpa.City;
import com.iwmn.a1.lead.service.CityService;
import com.iwmn.a1.lead.web.portlets.Constants;
import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;
import com.iwmn.a1.lead.web.portlets.api.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.List;

@Component(NetflixLeadDefaultAction.ACTION_NAME)
public class NetflixLeadDefaultAction implements Action {
    public static final String ACTION_NAME = "netflixLeadDefaultAction";

    @Autowired
    CityService cityService;

    @Override
    public ViewModel execute(PortletRequest request, PortletResponse response) throws IOException, PortletException {
        ViewModel model = new ViewModel(Constants.Pages.NETFLIX_LEAD_PAGE, Constants.Modules.NETFLIX_LEAD);
        List<City> cityList = cityService.findAllCitiesOrderByWeight();
        model.setAttribute("cityList", cityList);
        return model;
    }
}
