package com.iprt.di;

import java.lang.reflect.Field;
import com.iprt.di.annotations.Injectable;
import com.iprt.di.annotations.Singleton;
import com.iprt.di.annotations.Inject;

public class DIContainerTest {
 @Injectable
 @Singleton
 static class DummyService {
  @Inject
  private String someField;

  } 

public static void main(String[] args)throws Exception {
    Class<?> clazz = DummyService.class;

Injectable injectable = clazz.getAnnotation(Injectable.class);
if(injectable != null) {
  System.out.println("Found: " + injectable);
}

Singleton singleton = clazz.getAnnotation(Singleton.class);
if(singleton != null) {
 System.out.println("Found: " + singleton);
}

Field field = clazz.getDeclaredField("someField");
Inject inject = field.getAnnotation(Inject.class);
if(inject != null) {
 System.out.println("Found: " + inject);
    }
   }
 }
  

