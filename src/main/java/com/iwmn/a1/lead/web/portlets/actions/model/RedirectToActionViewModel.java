package com.iwmn.a1.lead.web.portlets.actions.model;


import com.iwmn.a1.lead.web.portlets.Constants;

public class RedirectToActionViewModel extends RedirectViewModel {

    public RedirectToActionViewModel(String action) {
        super("?" + Constants.Params.PORTLET_ACTION + "=" + action);
    }

}
