package com.iwmn.a1.lead.web.portlets.api;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.service.SchoolLeadService;
import com.liferay.portal.kernel.util.ParamUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/downloadSchoolLeads")
@Lazy
public class SchoolLeadApi {
	private static final long serialVersionUID = 1126422874492280897L;
	private static final String DOWNLOAD_KEY = "clasid6354asdf";

	SchoolLeadService service;

	public SchoolLeadApi() {
		this.service = ApplicationContextHolder.getBean(SchoolLeadService.class);
	}

	@RequestMapping(value = "/csv", method= RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (DOWNLOAD_KEY.equals(req.getParameter("downloadId"))) {
			resp.setContentType("text/csv");
			String contentDisposition = "%s; filename=\"%s\"";
			resp.setHeader("Content-Disposition", String.format(
					contentDisposition, "attachment", "school_lead_forms.csv"));
			OutputStream out = resp.getOutputStream();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = ParamUtil.getDate(req, "startDate", sdf, null);
			Date endDate = ParamUtil.getDate(req, "endDate", sdf, null);
			
			List<SchoolLeadModel> forms = service.findAllByCreationDate(startDate, endDate);
			byte[] nl = "\n".getBytes();
			out.write("Creation Date,Contact Person,School Name,City,Email,Phone Number,IP\n".getBytes());
			for(SchoolLeadModel model : forms){
				String row = String.format("%s,%s,%s,%s,%s,%s,%s", model.getCreationDate(), model.getContactPerson(),
				model.getSchoolName(), model.getCity(), model.getEmail(), model.getPhoneNumber(), model.getIp());
				out.write(row.getBytes());
				out.write(nl);
			}
			out.flush();
			out.close();
		}
	}
}
