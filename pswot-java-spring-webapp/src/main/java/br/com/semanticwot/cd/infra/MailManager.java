/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.infra;

import br.com.semanticwot.cd.models.SystemUser;
import br.com.semanticwot.cd.util.EmailTemplates;
import java.text.MessageFormat;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author nailton
 */
@Service
public class MailManager {
    
    @Autowired
    private JavaMailSenderImpl mailer;
    
    public void sendNewPurchaseMail(SystemUser user, String emailTemplate) throws MessagingException {

        Object[] args = {user.getName(), user.getUsername()};
        MessageFormat fmt = new MessageFormat(emailTemplate);

        MimeMessage message = mailer.createMimeMessage();

// use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

// use the true flag to indicate the text included is HTML
        helper.setText(fmt.format(args), true);
        
        System.out.println("Passei por aqui " + user.getUsername());
        
        //SimpleMailMessage email = new SimpleMailMessage();
        helper.setFrom(user.getUsername());
        
        // Somente por enquanto, que esta em teste. 
        // Em producao mudar para helper.setTo(user.getLogin());
        helper.setTo("notlian.junior@gmail.com");
        
        helper.setSubject("PSWoT Register");
        mailer.send(message);
    }
    
}
