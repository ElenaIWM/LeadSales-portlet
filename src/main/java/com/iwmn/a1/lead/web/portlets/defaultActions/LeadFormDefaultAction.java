package com.iwmn.a1.lead.web.portlets.defaultActions;

import com.iwmn.a1.lead.web.portlets.Constants;
import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;
import com.iwmn.a1.lead.web.portlets.api.Action;
import org.springframework.stereotype.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.List;

@Component(LeadFormDefaultAction.ACTION_NAME)
public class LeadFormDefaultAction implements Action {

    public static final String ACTION_NAME = "leadFormDefaultAction";

    @Override
    public ViewModel execute(PortletRequest request, PortletResponse response) throws IOException, PortletException {
        ViewModel model = new ViewModel(Constants.Pages.LEAD_FORM_PAGE, Constants.Modules.LEAD_FORM);
        return model;
    }
}
