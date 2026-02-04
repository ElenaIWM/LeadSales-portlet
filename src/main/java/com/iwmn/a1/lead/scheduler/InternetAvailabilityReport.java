package com.iwmn.a1.lead.scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.iwmn.a1.lead.model.jpa.InternetAvailabilityModel;
import com.iwmn.a1.lead.service.EmailService;
import com.iwmn.a1.lead.service.InternetAvailabilityService;
import com.iwmn.a1.lead.service.NetflixLeadService;
import com.iwmn.a1.lead.web.portlets.Constants;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class InternetAvailabilityReport {

	InternetAvailabilityService internetAvailabilityService;
	EmailService emailService;

	public InternetAvailabilityReport(InternetAvailabilityService internetAvailabilityService, EmailService emailService){
		this.internetAvailabilityService = internetAvailabilityService;
		this.emailService = emailService;
	}

	@Scheduled(cron = "0 0 5 ? * *")
	void sendInternetAvailabilityReport() {
		Date today = new Date();
		System.out.println("InternetAvailabilityReport timestamp " + today.toString());
		
		Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
		
		System.out.println("InternetAvailabilityReport from " + yesterday.toString());
		
		List<InternetAvailabilityModel> results = internetAvailabilityService.findAllByCreationDate(yesterday, today);
		List<InternetAvailabilityModel> resultsPrivate = new ArrayList<InternetAvailabilityModel>();
		List<InternetAvailabilityModel> resultsBusiness = new ArrayList<InternetAvailabilityModel>();
		
		for(InternetAvailabilityModel model : results) {
			if(model!=null) {
				if(model.getUserType()!=null && model.getUserType().equals(Constants.USER_TYPE_BUSINESS)) {
					resultsBusiness.add(model);
				}else {
					resultsPrivate.add(model);
				}
			}
		}

		Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH)+1;
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	File attachmentPrivate = new File("internet_availability_private_"+String.valueOf(day)+"_"+String.valueOf(month)+"_"+String.valueOf(year)+".csv");
    	File attachmentBusiness = new File("internet_availability_business_"+String.valueOf(day)+"_"+String.valueOf(month)+"_"+String.valueOf(year)+".csv");
    	
	    if(resultsPrivate!=null && resultsPrivate.size()>0) {
	    	System.out.println("results Private size = " + resultsPrivate.size());
	    	try {
				OutputStream outputStream = new FileOutputStream(attachmentPrivate);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
				outputStream.write("City,Address,Phone Number,URL,Date,IP \n".getBytes());
				for (InternetAvailabilityModel data : resultsPrivate) {
				    StringBuilder line = new StringBuilder();
				    line.append(data.getCity());
				    line.append(',');
				    line.append(data.getAddressLatin());
				    line.append(',');
				    line.append(data.getMsisdn());
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
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }else {
	    	System.out.println("result private size = 0");
	    }
	    
	    if(resultsBusiness!=null && resultsBusiness.size()>0) {
	    	System.out.println("results Business size = " + resultsBusiness.size());
	    	
	    	try {
				OutputStream outputStream = new FileOutputStream(attachmentBusiness);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
				outputStream.write("City,Address,Phone Number,URL,Date \n".getBytes());
				for (InternetAvailabilityModel data : resultsBusiness) {
				    StringBuilder line = new StringBuilder();
				    line.append(data.getCity());
				    line.append(',');
				    line.append(data.getAddressLatin());
				    line.append(',');
				    line.append(data.getMsisdn());
				    line.append(',');
				    line.append(data.getUrl());
				    line.append(',');
				    line.append(data.getCreationDate());				   
				    line.append("\n");
				    outputStreamWriter.write(line.toString());
				}
				outputStreamWriter.flush();
				outputStreamWriter.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }else {
	    	System.out.println("result business size = 0");
	    }
	    
	    if((resultsPrivate!=null && resultsPrivate.size()>0) || (resultsBusiness!=null && resultsBusiness.size()>0)) {
	    	emailService.sendInternetAvailabilityReport(attachmentPrivate, attachmentBusiness);
	    }

	}

}
