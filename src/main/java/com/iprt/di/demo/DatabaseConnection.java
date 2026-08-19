package com.iprt.di.demo;

import com.iprt.di.annotations.Injectable;
import com.iprt.di.annotations.Singleton;

@Injectable
@Singleton
public class DatabaseConnection {
 public void connect() {
  System.out.println("Connected to database");
  }
}
