package com.iwmn.a1.lead.model.exceptions;

public class RedirectViewModelException extends RuntimeException {
    private String location;


    public RedirectViewModelException(String location) {
        super();
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
