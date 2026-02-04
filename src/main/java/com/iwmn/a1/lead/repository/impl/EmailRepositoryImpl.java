package com.iwmn.a1.lead.repository.impl;

import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.repository.EmailRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;

@Profile({"prod", "preprod"})
@Repository
public class EmailRepositoryImpl implements EmailRepository {
    private Session session;

    private static final String EMAIL_SUBJECT = "Lead form contact";
    private static final String CUSTOMER_EMAIL_FROM = "noreply_webform@a1.mk";

    public EmailRepositoryImpl(Session session) {
        this.session = session;
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
    }

    @Override
    public void sendLeadForm(LeadSalesModel model, String emailTo) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(model.getEmail()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            message.setSubject(EMAIL_SUBJECT);
            message.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
            if(model.getCustomerType()!=null && model.getCustomerType().equals("CUSTOMER_BUSINESS")) {
                message.setContent(String.format(
                                "Zdravo,"
                                        + "<br /><br /> Preku web e popolneta Lead forma. <br /><br /> "
                                        + "Informacii:<br /> "
                                        + "Ime na firma: %s <br /> "
                                        + "Lice za kontakt: %s <br /> "
                                        + "Danochen broj: %s <br /> "
                                        + "Telefon: %s <br /> "
                                        + "Email: %s <br /> "
                                        + "Comment: %s <br /> "
                                        + "URL: %s <br /><br /> "
                                        + "Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ",
                                model.getCompanyName(), model.getContactPerson(), model.getTaxNumber(),
                                model.getPhoneNumber(), model.getEmail(),
                                model.getFormComment(), model.getUrl() ),
                        "text/html; charset=\"utf-8\"");
            }else if(model.getInstalationAddress()==null || model.getInstalationAddress().isEmpty()) {
                if(model.getCompanyName()!=null && !model.getCompanyName().equals("")){
                    message.setContent(String.format(
                                    "Zdravo,"
                                            + "<br /><br /> Preku web e popolneta Lead forma. <br /><br /> "
                                            + "Informacii:<br /> "
                                            + "Ime i prezime: %s <br /> "
                                            + "Ime na firma: %s <br /> "
                                            + "Telefon: %s <br /> "
                                            + "Email: %s <br /> "
                                            + "Comment: %s <br /> "
                                            + "URL: %s <br /><br /> "
                                            + "Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ",
                                    model.getFullName(), model.getCompanyName(), model.getPhoneNumber(), model.getEmail(),
                                    model.getFormComment(), model.getUrl() ),
                            "text/html; charset=\"utf-8\"");
                }else{
                    message.setContent(String.format(
                                    "Zdravo,"
                                            + "<br /><br /> Preku web e popolneta Lead forma. <br /><br /> "
                                            + "Informacii:<br /> "
                                            + "Ime i prezime: %s <br /> "
                                            + "Telefon: %s <br /> "
                                            + "Email: %s <br /> "
                                            + "Comment: %s <br /> "
                                            + "URL: %s <br /><br /> "
                                            + "Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ",
                                    model.getFullName(), model.getPhoneNumber(), model.getEmail(),
                                    model.getFormComment(), model.getUrl() ),
                            "text/html; charset=\"utf-8\"");
                }
            }else {
                if(model.getCompanyName()!=null && !model.getCompanyName().equals("")){
                    message.setContent(String.format(
                                    "Zdravo,"
                                            + "<br /><br /> Preku web e popolneta Lead forma. <br /><br /> "
                                            + "Informacii:<br /> "
                                            + "Ime i prezime: %s <br /> "
                                            + "Ime na firma: %s <br /> "
                                            + "Telefon: %s <br /> "
                                            + "Email: %s <br /> "
                                            + "Instalation Address: %s <br /> "
                                            + "Comment: %s <br /> "
                                            + "URL: %s <br /><br /> "
                                            + "Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ",
                                    model.getFullName(), model.getCompanyName(), model.getPhoneNumber(), model.getEmail(),
                                    model.getInstalationAddress(), model.getFormComment(), model.getUrl() ),
                            "text/html; charset=\"utf-8\"");
                }else{
                    message.setContent(String.format(
                                    "Zdravo,"
                                            + "<br /><br /> Preku web e popolneta Lead forma. <br /><br /> "
                                            + "Informacii:<br /> "
                                            + "Ime i prezime: %s <br /> "
                                            + "Telefon: %s <br /> "
                                            + "Email: %s <br /> "
                                            + "Instalation Address: %s <br /> "
                                            + "Comment: %s <br /> "
                                            + "URL: %s <br /><br /> "
                                            + "Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ",
                                    model.getFullName(), model.getPhoneNumber(), model.getEmail(),
                                    model.getInstalationAddress(), model.getFormComment(), model.getUrl() ),
                            "text/html; charset=\"utf-8\"");
                }
            }


            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendLeadFormToCustomer(String emailTo, String languageId) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(CUSTOMER_EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            if(languageId!=null && languageId.equals("en_US")){
                message.setSubject("Delivery Notification");
                message.setContent("Dear Sir/Madam,<br /> <br />" +
                                "Thank you for your inquiry. We have received your message and we will contact you as soon as possible.<br /> <br />" +
                                "Best regards, A1 <br /> <br />" +
                                "<img src=\"https://www.a1.mk/o/a1-support/images/a1-logo.png\">",
                        "text/html; charset=\"utf-8\"");
            }else if(languageId!=null && languageId.equals("sq_AL")){
                message.setSubject("Vërtetim për pranimin e mesazhit");
                message.setContent("Të nderuar, <br /> <br />" +
                                "Ju falënderojmë për interesimin. Mesazhi juaj është pranuar dhe do Ju kontaktojmë në afatin më të shkurtër të mundshëm.<br /> <br />" +
                                "I juaj A1 <br /> <br />" +
                                "<img src=\"https://www.a1.mk/o/a1-support/images/a1-logo.png\">",
                        "text/html; charset=\"utf-8\"");
            }else{
                message.setSubject("Потврда за примена порака");
                message.setContent("Почитувани,<br /> <br />" +
                                "Ви благодариме за интересот. Вашата порака е примена и ќе Ве контактираме во најкраток можен рок.<br /> <br />" +
                                "Ваш A1 <br /> <br />" +
                                "<img src=\"https://www.a1.mk/o/a1-support/images/a1-logo.png\">",
                        "text/html; charset=\"utf-8\"");
            }

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNetflixLeadReport(File attachment) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@a1.mk"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tsteamleaders@a1.mk,elinda.stojanovamilosheska@a1.mk,elena@iwmnetwork.com"));
            message.setSubject("Netflix lead report");
            String messageText = "Zdravo, "+
                    "<br /><br /> Vo prilog se popolnetite lead formi za netflix vo poslednite 24 chasa. " +
                    "<br /> Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ";
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageText, "text/html; charset=\"utf-8\"");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            if(attachment!=null && attachment.length()>0) {
                attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(attachment);
                multipart.addBodyPart(attachmentBodyPart);
            }

            message.setContent(multipart);

            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");

            // Additional elements to make DSN work
            mc.addMailcap("multipart/report;;  x-java-content-handler=com.sun.mail.dsn.multipart_report");
            mc.addMailcap("message/delivery-status;; x-java-content-handler=com.sun.mail.dsn.message_deliverystatus");
            mc.addMailcap("message/disposition-notification;; x-java-content-handler=com.sun.mail.dsn.message_dispositionnotification");
            mc.addMailcap("text/rfc822-headers;;   x-java-content-handler=com.sun.mail.dsn.text_rfc822headers");

            Transport.send(message);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void sendSchoolLeadForm(SchoolLeadModel model) {
        String INTERNET_AVAILABILITY_EMAIL_FROM = "no-reply@a1.mk";
        String INTERNET_AVAILABILITY_EMAIL_TO = "Corporate.Communications@a1.mk,Jana.Arsovska-Esmerova@a1.mk";
        String INTERNET_AVAILABILITY_EMAIL_SUBJECT = "School lead form";

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(INTERNET_AVAILABILITY_EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(INTERNET_AVAILABILITY_EMAIL_TO));
            message.setSubject(INTERNET_AVAILABILITY_EMAIL_SUBJECT);
            message.setHeader("Content-Type", "text/html; charset=\"utf-8\"");

            message.setContent(String.format(
                            "Zdravo,"
                                    + "<br /><br /> Preku web e popolneta forma za prijavuvanje na uchilishte. <br /><br /> "
                                    + "Ime na uciliste: <br /> "
                                    + "Grad: %s <br /> "
                                    + "Nastavnik za kontakt: %s <br /> "
                                    + "Telefon: %s <br /> "
                                    + "Email: %s <br /> "
                                    + "Prijaveno od: %s <br /><br /> "
                                    + "Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ",
                            model.getSchoolName(), model.getCity(), model.getContactPerson(), model.getPhoneNumber(), model.getEmail(), model.getPersonType() ),
                    "text/html; charset=\"utf-8\"");

            Transport.send(message);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void sendInternetAvailabilityReport(File attachFilePrivate, File attachFileBusiness) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@a1.mk"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("cs.ftth@a1.mk,elinda.stojanovamilosheska@a1.mk"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("elinda.stojanovamilosheska@a1.mk,filip.milkov@a1.mk,nikola.stojanchevski@a1.mk,elena@iwmnetwork.com"));
            message.setSubject("Internet availability report");
            String messageText = "Zdravo, "+
                    "<br /><br /> Vo prilog se popolnetite baranja za internet dostapnost vo poslednite 24 chasa. " +
                    "<br /> Ve molime prosledete go ova baranje. <br /><br /> Pozdrav,<br />Web Team ";
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageText, "text/html; charset=\"utf-8\"");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            if(attachFilePrivate!=null && attachFilePrivate.length()>0) {
                attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(attachFilePrivate);
                multipart.addBodyPart(attachmentBodyPart);
            }

            if(attachFileBusiness!=null && attachFileBusiness.length()>0) {
                attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(attachFileBusiness);
                multipart.addBodyPart(attachmentBodyPart);
            }

            message.setContent(multipart);

            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");

            // Additional elements to make DSN work
            mc.addMailcap("multipart/report;;  x-java-content-handler=com.sun.mail.dsn.multipart_report");
            mc.addMailcap("message/delivery-status;; x-java-content-handler=com.sun.mail.dsn.message_deliverystatus");
            mc.addMailcap("message/disposition-notification;; x-java-content-handler=com.sun.mail.dsn.message_dispositionnotification");
            mc.addMailcap("text/rfc822-headers;;   x-java-content-handler=com.sun.mail.dsn.text_rfc822headers");

            Transport.send(message);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
