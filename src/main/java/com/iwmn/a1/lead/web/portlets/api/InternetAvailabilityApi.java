package com.iwmn.a1.lead.web.portlets.api;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.InternetAvailabilityModel;
import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.service.InternetAvailabilityService;
import com.iwmn.a1.lead.service.LeadSalesService;
import com.liferay.portal.kernel.util.ParamUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/downloadInternetAvailability")
@Lazy
public class InternetAvailabilityApi {

    private static final String DOWNLOAD_KEY = "alasid6354asdf";

    InternetAvailabilityService service;

    public InternetAvailabilityApi() {
        this.service = ApplicationContextHolder.getBean(InternetAvailabilityService.class);
    }


    @RequestMapping(value = "/csv", method= RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (DOWNLOAD_KEY.equals(req.getParameter("downloadId"))) {
            resp.setContentType("text/csv");
            String contentDisposition = "%s; filename=\"%s\"";
            resp.setHeader("Content-Disposition", String.format(
                    contentDisposition, "attachment", "internet_availability.csv"));
            OutputStream out = resp.getOutputStream();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = ParamUtil.getDate(req, "startDate", sdf, null);
            Date endDate = ParamUtil.getDate(req, "endDate", sdf, null);

            List<InternetAvailabilityModel> forms = service.findAllByCreationDate(startDate, endDate);
            byte[] nl = "\n".getBytes();
            out.write("City,Address,Phone Number,User Type,URL,Date,IP \n".getBytes());
            for(InternetAvailabilityModel model : forms){
                String row = String.format("%s,%s,%s,%s,%s,%s,%s", model.getCity(), model.getAddressLatin(),
                        model.getMsisdn(), model.getUserType(), model.getUrl(), model.getCreationDate(),
                        model.getIp());
                out.write(row.getBytes());
                out.write(nl);
            }
            out.flush();
            out.close();
        }
    }
}
