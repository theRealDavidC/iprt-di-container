package com.iprt.di.demo;

import com.iprt.di.annotations.Singleton;
import com.iprt.di.annotations.Injectable;

@Injectable
@Singleton
public class EmailService {
 public void sendEmail() {
  System.out.println("Email sent");
   }
 }
