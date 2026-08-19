package com.iprt.di.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import com.iprt.di.annotations.Inject;
import com.iprt.di.container.DIContainer;

public class ReflectionUtils {
 public static List<Field> getInjectableFields(Class<?> clazz) {
  Field[] fields = clazz.getDeclaredFields();
  List<Field> result = new ArrayList<>();
  
  for(Field classField : fields) {
   if(classField.isAnnotationPresent(Inject.class)) {
     result.add(classField);
     }
    }
    return result;
  }
  
 public static Constructor<?> getInjectableConstructor(Class<?> clazz) throws Exception {
  Constructor[] constructors = clazz.getDeclaredConstructors();
  
  for(Constructor classConstructor : constructors) {
   if(classConstructor.isAnnotationPresent(Inject.class)) {
     return classConstructor;
    } 
   }
   return clazz.getDeclaredConstructor();
    }
    
  public static Object createInstance(Class<?> clazz, Object... args) throws Exception {
   Constructor<?> constructor = getInjectableConstructor(clazz);
   constructor.setAccessible(true);
   return constructor.newInstance(args);
   }
   
  public static void injectFields(Object instance, DIContainer container) throws Exception {
    List<Field> fields = getInjectableFields(instance.getClass());
    
    for(Field field : fields) {
      field.setAccessible(true);
      Object value = container.resolve(field.getType());
      field.set(instance, value);
      }
     }
}
