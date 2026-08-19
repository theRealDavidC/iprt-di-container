package com.iprt.di.demo;

import com.iprt.di.annotations.Injectable;
import com.iprt.di.annotations.Inject;

@Injectable 
public class OrderService {
 @Inject
 private OrderRepository orderRepository;
 @Inject
 private NotificationService notificationService;
 
 public void placeOrder() {
    orderRepository.saveOrder();
    notificationService.sendNotification();
    System.out.println("Order placed");
 }
}
