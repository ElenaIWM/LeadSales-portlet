package com.iwmn.a1.lead.web.portlets.defaultActions;

import com.iwmn.a1.lead.web.portlets.Constants;
import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;
import com.iwmn.a1.lead.web.portlets.api.Action;
import org.springframework.stereotype.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;

@Component(LeadModalDefaultAction.ACTION_NAME)
public class LeadModalDefaultAction implements Action {

    public static final String ACTION_NAME = "leadModalDefaultAction";

    @Override
    public ViewModel execute(PortletRequest request, PortletResponse response) throws IOException, PortletException {
        ViewModel model = new ViewModel(Constants.Pages.LEAD_MODAL_PAGE, Constants.Modules.LEAD_MODAL);
        return model;
    }

}
