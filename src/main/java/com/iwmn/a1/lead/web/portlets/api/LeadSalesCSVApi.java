package com.iwmn.a1.lead.web.portlets.api;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iwmn.a1.lead.config.ApplicationContextHolder;
import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.service.LeadSalesService;
import com.liferay.portal.kernel.util.ParamUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("downloadLeadSales")
@Lazy
public class LeadSalesCSVApi {

	/**
	 * 
	 */
	private static final String DOWNLOAD_KEY = "alasid6354asdf";

	LeadSalesService service;

	public LeadSalesCSVApi() {
		this.service = ApplicationContextHolder.getBean(LeadSalesService.class);
	}


	@RequestMapping(value = "/csv", method= RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (DOWNLOAD_KEY.equals(req.getParameter("downloadId"))) {
			resp.setContentType("text/csv");
			String contentDisposition = "%s; filename=\"%s\"";
			resp.setHeader("Content-Disposition", String.format(
					contentDisposition, "attachment", "lead_forms.csv"));
			OutputStream out = resp.getOutputStream();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = ParamUtil.getDate(req, "startDate", sdf, null);
			Date endDate = ParamUtil.getDate(req, "endDate", sdf, null);
			
			List<LeadSalesModel> forms = service.findAllByCreationDate(startDate, endDate);
			byte[] nl = "\n".getBytes();
			out.write("CreationDate,Lead form type,Name,Company Name,Phone Number,Email,URL,IP,Email To\n".getBytes());
			for(LeadSalesModel model : forms){
				String row = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", model.getCreationDate(), model.getLeadType(),
				model.getFullName(), model.getCompanyName(), model.getPhoneNumber(), model.getEmail(),
				model.getUrl(), model.getIp(), model.getEmailTo());
				out.write(row.getBytes());
				out.write(nl);
			}
			out.flush();
			out.close();
		}
	}
}
