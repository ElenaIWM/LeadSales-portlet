package com.iwmn.a1.lead.web.portlets.api;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.service.EmailService;
import com.iwmn.a1.lead.service.LeadSalesService;
import com.iwmn.a1.lead.web.portlets.Constants;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
@Lazy
public class LeadSalesApi {

    /**
     *
     */
    private static final long serialVersionUID = 667718426151727281L;

    private static final String DOWNLOAD_KEY = "alasid6354asdf";

    private static final String template = "Hello %s!";

    public static final Pattern PHONE_NUMBER_REGEX = Pattern.compile(
            "^389[0-9]{8}$", Pattern.CASE_INSENSITIVE);


    LeadSalesService service;

    EmailService emailService;

    public LeadSalesApi() {
        this.service = ApplicationContextHolder.getBean(LeadSalesService.class);
        this.emailService = ApplicationContextHolder.getBean(EmailService.class);
    }

    @RequestMapping(value = "/insertLead", method= RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//		String token = ParamUtil.getString(request, "token");
//		System.out.println("lead saleas api ");
//		System.out.println("token " + token);
//		boolean isCaptchaValid = ActionUtil.isCaptchaValid(token);
        boolean isCaptchaValid = true;
//		System.out.println("isCaptchaValid " + isCaptchaValid);
        if(isCaptchaValid) {
            String customerType = ParamUtil.getString(request, "customerType");
            String fullName = ParamUtil.getString(request,"fullName");
            String phone = ParamUtil.getString(request,"phone");
            String email = ParamUtil.getString(request,"email");
            String comment = ParamUtil.getString(request,"comment");
            String currentUrl = ParamUtil.getString(request,"currentUrl");
            String companyName = ParamUtil.getString(request,"companyName");
            String address = ParamUtil.getString(request, "address");
            String taxNumber = ParamUtil.getString(request, "taxNumber");
            String contactPerson = ParamUtil.getString(request, "contactPerson");
            String remAddr = request.getHeader("HTTP-X-ASMP-FORWARDED-FOR");
            if (remAddr == null) {
                remAddr = request.getRemoteAddr();
            }

            System.out.println("remAddr "+ remAddr);

//			System.out.println("customerType " + customerType);
//			System.out.println("fullName " + fullName);
//			System.out.println("phone " + phone);
//			System.out.println("email " + email);
//			System.out.println("comment " + comment);
//			System.out.println("currentUrl " + currentUrl);
//			System.out.println("companyName " + companyName);
//			System.out.println("address " + address);
//			System.out.println("taxNumber " + taxNumber);
//			System.out.println("contactPerson " + contactPerson);


            if(phone!=null && !phone.equals("") && isValidPhone(phone) &&
                    email!=null && !email.equals("") &&
                    currentUrl!=null && !currentUrl.equals("")){

                LeadSalesModel model = new LeadSalesModel();
                model.setFullName(fullName);
                model.setPhoneNumber(phone);
                model.setEmail(email);
                model.setFormComment(comment);
                model.setUrl(currentUrl);
                model.setCompanyName(companyName);
                model.setInstalationAddress(address);
                model.setLeadType(Constants.LEAD_CARE_STRING);
                model.setCustomerType(customerType);
                model.setTaxNumber(taxNumber);
                model.setContactPerson(contactPerson);
                model.setIp(remAddr);

                String leadEmail = Constants.LEAD_SALES_EMAIL_TO;
                if(customerType!=null && customerType.equals("CUSTOMER_BUSINESS")) {
                    leadEmail = "SohoOnlineSales@a1.mk";
                }
                leadEmail+=",elinda.stojanovamilosheska@a1.mk";
                model.setEmailTo(leadEmail);

                emailService.sendLeadForm(model, leadEmail);

                String languageId = LanguageUtil.getLanguageId(request);

                emailService.sendLeadFormToCustomer(model.getEmail(), languageId);

                Date now = new Date();
                model.setCreationDate(now);
                //database column limit 255
                try {
                    service.save(model);
                }catch(Exception e) {
                    currentUrl = currentUrl.substring(0,250);
                    model.setUrl(currentUrl);
                    service.save(model);
                }

                request.setAttribute("success", true);
            }else {

            }
        }
    }

    public Boolean isValidPhone(String phone) {
        Matcher matcher = PHONE_NUMBER_REGEX.matcher(phone);
        return matcher.find();
    }

}