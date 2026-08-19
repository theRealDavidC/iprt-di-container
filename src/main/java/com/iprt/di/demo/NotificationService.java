package com.iprt.di.demo;

import com.iprt.di.annotations.Injectable;
import com.iprt.di.annotations.Inject;

@Injectable
public class NotificationService {
 @Inject 
 private EmailService emailService;
 
 public void sendNotification() {
    emailService.sendEmail();
    System.out.println("Notification sent");
 }
}
