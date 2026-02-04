package com.iwmn.a1.lead.web.portlets;

import java.util.regex.Pattern;

public class Constants {
    public static String LOGIN_LOCATION = null;

    public static final String LEAD_SALES = "1";
    public static final String LEAD_CARE = "2";
    public static final String LEAD_OTHER = "3";
    public static final String LEAD_SALES_STRING = "Lead-sales";
    public static final String LEAD_CARE_STRING = "Lead-care";
    public static final String LEAD_OTHER_STRING = "Lead-other";
    public static final String USER_TYPE_PRIVATE = "private";
    public static final String USER_TYPE_BUSINESS = "business";
    public static final String INSTALLATION_ADDRESS = "installationAddress";
    public static final String SHOW_SURVEY = "showSurvey";
    public static final String SHOW_PERSONAL_DATA = "agreePersonalData";

    public static final String LEAD_SALES_EMAIL_TO = "leadform-sales@a1.mk";
    public static final String LEAD_CARE_EMAIL_TO = "leadform-care@a1.mk";

    public static final Pattern PHONE_NUMBER_REGEX = Pattern.compile(
            "^389[0-9]{8}$", Pattern.CASE_INSENSITIVE);


    public static class Pages {
        public static final String LEAD_FORM_PAGE = "/pages/lead-form-page.jsp";
        public static final String LEAD_MODAL_PAGE = "/pages/lead-modal-page.jsp";
        public static final String NETFLIX_LEAD_PAGE = "/pages/netflix-lead-page.jsp";
        public static final String SCHOOL_LEAD_PAGE = "/pages/school-lead-page.jsp";

        public static final String AVAILABILITY_INTERNET_SERVICES_PAGE = "/pages/availability-internet-services-page.jsp";
    }

    public static class Modules {
        public static final String DASHBOARD = "/modules/dashboard.jsp";
        public static final String LEAD_FORM = "/modules/leadForm/view.jsp";
        public static final String LEAD_MODAL = "/modules/leadModal/view.jsp";
        public static final String NETFLIX_LEAD = "/modules/netflixLead/view.jsp";
        public static final String SCHOOL_LEAD = "/modules/schoolLead/view.jsp";

        public static final String AVAILABILITY_INTERNET_SERVICES_FORM = "/modules/availabilityInternetServices/view.jsp";
    }

    public static class Params {

        public static final String PORTLET_ACTION = "portletAction";
        public static final String FULL_NAME = "fullName";
        public static final String PHONE_NUMBER = "phone";
        public static final String EMAIL = "email";
        public static final String COMMENT = "comment";
        public static final String CURRENT_URL = "currentUrl";
        public static final String SHOW_SURVEY = "showSurvey";

        public static final String COMPANY_NAME = "companyName";
        public static final String INSTALLATION_ADDRESS = "instAddress";

        public static final String SCHOOL_NAME = "schoolName";
        public static final String CITY = "city";
        public static final String CONTACT_PERSON = "contactPerson";
        public static final String MSISDN = "msisdn";
        public static final String PERSON_TYPE = "personType";

        public static final String USER_TYPE = "userType";
        public static final String CITY_CYRILIC = "cityCyrilic";
        public static final String ADDRESS = "address";
        public static final String WEBSITE = "website";
    }

    public static class Attributes {

        public static final String INCLUDE_MODULE = "includeModule";
        public static final String STATUS = "status";
        public static final String ERROR = "error";
        public static final String SCRIPT = "script";
        public static final String JSP_PAGE = "jspPage";
        public static final String LOGIN_URL = "loginUrl";

    }

    public static class MESSAGES {
        public static final String LEAD_FORM_ERROR = "lead-form-error";
        public static final String LEAD_FORM_SUCCESS = "lead-form-success";
    }


}
