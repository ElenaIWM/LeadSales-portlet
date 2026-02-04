package com.iwmn.a1.lead.web.portlets.actions.model;

import com.iwmn.a1.lead.model.exceptions.RedirectViewModelException;

import javax.portlet.PortletRequest;
import java.util.stream.Collectors;

public class RedirectViewModel extends ViewModel {

    private String redirectLocation;

    public RedirectViewModel(String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }

    public String apply(PortletRequest request) {
        StringBuilder sb = new StringBuilder(this.redirectLocation);
        String query = this.attributes
                .entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        if (!query.isEmpty()) {
            if (this.redirectLocation.contains("?")) {
                sb.append("&").append(query);
            } else {
                sb.append("?").append(query);
            }
        }
        throw new RedirectViewModelException(sb.toString());


    }


}
