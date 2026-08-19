package com.iprt.di.demo;

import com.iprt.di.container.DIContainer;

public class DemoApp {
 public static void main(String[] args) throws Exception {
  DIContainer container = new DIContainer();
  container.register(DatabaseConnection.class);
  container.register(EmailService.class);
  container.register(OrderRepository.class);
  container.register(NotificationService.class);
  container.register(OrderService.class);
  
  OrderService service = container.resolve(OrderService.class);
  service.placeOrder();
  }
}
