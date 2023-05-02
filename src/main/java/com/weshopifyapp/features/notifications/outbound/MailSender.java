package com.weshopifyapp.features.notifications.outbound;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.weshopifyapp.features.notifications.beans.NotificationsBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Component
@Slf4j
public class MailSender {

	@Autowired
	private Configuration freeMarkerConfig;

	@Value("${sendgrid.api.key}")
	private String sendGridApiKey;
	
	@Value("${sendgrid.api.verifyEmail}")
	private String verificationEmailLink;

	@Value("${sendgrid.api.from}")
	private String fromAddress;
	
	public NotificationsBean sendVerificationEmail(NotificationsBean notificationData, Map<String, Object> model) {

		notificationData.getTo().stream().forEach(emailRequest -> {
			try {
				model.put("reciever", emailRequest.getEmail());
				model.put("userId", emailRequest.getUserId());
				model.put("verifyEmailLink", verificationEmailLink+emailRequest.getId());
				Template template = freeMarkerConfig.getTemplate("signup_email.ftl");
				String templateWithData = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				Email from = new Email(fromAddress);
				String subject = notificationData.getSubject();
				Email to = new Email(emailRequest.getEmail());
				Content content = new Content(notificationData.getContentType(), templateWithData);
				Mail mail = new Mail(from, subject, to, content);
				
				
				SendGrid sg = new SendGrid(sendGridApiKey);
				Request request = new Request();

				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				Response response = sg.api(request);
				System.out.println(response.getStatusCode());
				System.out.println(response.getBody());
				System.out.println(response.getHeaders());
				notificationData.setStatusCode(response.getStatusCode());
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
			}
		});

		return notificationData;
	}
}
