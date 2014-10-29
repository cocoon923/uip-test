package com.ailife.uip.test.email;

import com.ailife.uip.test.config.EmailProperties;
import com.ailife.uip.test.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Created by chenmm on 10/14/2014.
 */
public class EmailImpl implements IEmail {

	@Autowired
	private Session session;

	@Autowired
	private EmailProperties emailProperties;

	private Message message;

	@PostConstruct
	private void initial() {
		message = new MimeMessage(session);
	}

	@Override
	public void sendEmail(String toEmail, String content) {
		try {
			message.setFrom(new InternetAddress(emailProperties.getFromEmail()));
			InternetAddress[] address = {new InternetAddress(toEmail)};
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject("账号激活邮件");
			message.setSentDate(new Date());
			message.setContent(content, "text/html;charset=utf-8");

			//Send the message
			Transport.send(message);
		} catch (MessagingException e) {
			LogUtil.error(this.getClass(), e, "Send Email Error");
		}
	}

}
