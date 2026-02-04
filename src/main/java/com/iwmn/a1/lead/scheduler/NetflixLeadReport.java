package com.iwmn.a1.lead.scheduler;

import com.iwmn.a1.lead.model.jpa.NetflixLeadModel;
import com.iwmn.a1.lead.service.EmailService;
import com.iwmn.a1.lead.service.NetflixLeadService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Component
public class NetflixLeadReport {

	NetflixLeadService netflixLeadService;
	EmailService emailService;

	public NetflixLeadReport(NetflixLeadService netflixLeadService, EmailService emailService){
		this.netflixLeadService = netflixLeadService;
		this.emailService = emailService;
	}

	@Scheduled(cron = "0 0 5 ? * *")
//    @Scheduled(cron = "0 1/1 * ? * *")
	void sendNetflixLeadReportReport() {
		Date today = new Date();
		System.out.println("NetflixLeadReport timestamp " + today.toString());

		Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);

		System.out.println("NetflixLeadReport from " + yesterday.toString());

		List<NetflixLeadModel> netflixLeadList = netflixLeadService.findAllByCreationDate(yesterday, today);

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		File attachment = new File("netflix_lead_"+String.valueOf(day)+"_"+String.valueOf(month)+"_"+String.valueOf(year)+".csv");
		if(netflixLeadList!=null && !netflixLeadList.isEmpty()) {
			try {
				OutputStream outputStream = new FileOutputStream(attachment);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
				outputStream.write("City,Phone Number,URL,Date,IP \n".getBytes());
				for (NetflixLeadModel data : netflixLeadList) {
					StringBuilder line = new StringBuilder();
					line.append(data.getCity());
					line.append(',');
					line.append(data.getPhoneNumber());
					line.append(',');
					line.append(data.getUrl());
					line.append(',');
					line.append(data.getCreationDate());
					line.append(',');
					line.append(data.getIp());
					line.append("\n");
					outputStreamWriter.write(line.toString());
				}
				outputStreamWriter.flush();
				outputStreamWriter.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			emailService.sendNetflixLeadReport(attachment);
		}else {
			System.out.println("NetflixLeadModel result size = 0");
		}
	}
}
