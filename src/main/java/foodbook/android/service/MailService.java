package foodbook.android.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import foodbook.android.model.Reservation;
import foodbook.android.model.User;


@Service
public class MailService {

    private JavaMailSender javaMailSender;

    public MailService(@Autowired JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMail(User user) throws MailException{
    	  SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
          simpleMailMessage.setTo(user.getEmail());
          simpleMailMessage.setFrom("panel@reservation4.me");
          simpleMailMessage.setSubject("Registration");

          String mailContent = "Thank You for registering on FoodBook! Please follow this link to confirm your registration :{URL_VALUE} \n\n";
         
          
          String url = "http://localhost:8080/api/user/confirmed-mail/" + user.getId();
          mailContent = mailContent.replace("{URL_VALUE}", url);
          
   
          System.out.println("BEFORE SENDING TO "+user.getEmail());

          simpleMailMessage.setText(mailContent);
          javaMailSender.send(simpleMailMessage);

          System.out.println("AFTER SENDING TO "+user.getEmail());
    }

	public void sendReservationInvitationMail(User user, Reservation created) {
		  SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	      simpleMailMessage.setTo(user.getEmail());
	        simpleMailMessage.setFrom("panel@reservation4.me");
	        simpleMailMessage.setSubject("Invitation");

	        String mailContent = "You have been invited to attent a reservation at " + created.getRestaurant().getName() 
	        		+ " please follow this link to confirm your attendance :{URL_VALUE} \n\n";
	       
	        
	        String url = "http://localhost:8080/#/guest/reservation-details/" + created.getId();
	        mailContent = mailContent.replace("{URL_VALUE}", url);
	        
	 
	        System.out.println("BEFORE SENDING TO "+user.getEmail());

	        simpleMailMessage.setText(mailContent);
	        javaMailSender.send(simpleMailMessage);

	        System.out.println("AFTER SENDING TO "+ user.getEmail());
		
	}

	
}
