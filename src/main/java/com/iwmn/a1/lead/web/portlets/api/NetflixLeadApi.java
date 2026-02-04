package com.iwmn.a1.lead.web.portlets.api;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.NetflixLeadModel;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.service.NetflixLeadService;
import com.iwmn.a1.lead.service.SchoolLeadService;
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
@RequestMapping("/downloadNetflixLeads")
@Lazy
public class NetflixLeadApi {

    private static final long serialVersionUID = 1126422874492280897L;
    private static final String DOWNLOAD_KEY = "clasid6354asdf";

    NetflixLeadService service;

    public NetflixLeadApi() {
        this.service = ApplicationContextHolder.getBean(NetflixLeadService.class);
    }

    @RequestMapping(value = "/csv", method= RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (DOWNLOAD_KEY.equals(req.getParameter("downloadId"))) {
            resp.setContentType("text/csv");
            String contentDisposition = "%s; filename=\"%s\"";
            resp.setHeader("Content-Disposition", String.format(
                    contentDisposition, "attachment", "netflix_leads.csv"));
            OutputStream out = resp.getOutputStream();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = ParamUtil.getDate(req, "startDate", sdf, null);
            Date endDate = ParamUtil.getDate(req, "endDate", sdf, null);

            List<NetflixLeadModel> forms = service.findAllByCreationDate(startDate, endDate);
            byte[] nl = "\n".getBytes();
            out.write("CreationDate,City,Phone Number,URL,IP\n".getBytes());
            for(NetflixLeadModel model : forms){
                String row = String.format("%s,%s,%s,%s,%s", model.getCreationDate(),
                        model.getCity(), model.getPhoneNumber(), model.getUrl(), model.getIp());
                out.write(row.getBytes());
                out.write(nl);
            }
            out.flush();
            out.close();
        }
    }
}
