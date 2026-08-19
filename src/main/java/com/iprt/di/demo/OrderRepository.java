package com.iprt.di.demo;

import com.iprt.di.annotations.Injectable;
import com.iprt.di.annotations.Inject;

@Injectable
public class OrderRepository {
 @Inject
 private DatabaseConnection db;
  
 public void saveOrder() {
  db.connect();
  System.out.println("Order saved");
  }
}
