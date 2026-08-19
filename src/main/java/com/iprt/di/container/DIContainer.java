package com.iprt.di.container;

import java.util.Map;
import java.util.HashMap;
import com.iprt.di.annotations.Singleton;
import com.iprt.di.exception.BeanNotFoundException;
import com.iprt.di.exception.CircularDependencyException;
import com.iprt.di.util.ReflectionUtils;
import java.lang.reflect.Constructor;

public class DIContainer {
 private Map<Class<?>, BeanDefinition> registry = new HashMap<>();
 
 public void register(Class<?> type) {
   boolean isSingleton = type.isAnnotationPresent(Singleton.class);
   BeanScope scope;
  if(isSingleton) {
   scope = BeanScope.SINGLETON;
 } else {
  scope = BeanScope.PROTOTYPE;
    }
    
  BeanDefinition definition = new BeanDefinition(type, scope);
  registry.put(type, definition);
  }
  
 public <T> T resolve(Class<T> type) throws Exception {
  if(!registry.containsKey(type)) {
    throw new BeanNotFoundException("Bean not found: " + type.getName());
    }
  
  BeanDefinition definition = registry.get(type);
  if(definition.isResolving()) {
    throw new CircularDependencyException("Circular Dependency exception found: " + type.getName());
    }
    
  definition.setResolving(true);
  
  if(definition.getScope() == BeanScope.SINGLETON && definition.getInstance() != null) {
   return type.cast(definition.getInstance());
   }
   
  Constructor<?> constructor = ReflectionUtils.getInjectableConstructor(type);
 Class<?>[] paramTypes = constructor.getParameterTypes();
 Object[] params = new Object[paramTypes.length];
 for (int i = 0; i < paramTypes.length; i++) {
    params[i] = resolve((Class) paramTypes[i]);
 }

Object instance = ReflectionUtils.createInstance(type, params);
  ReflectionUtils.injectFields(instance, this);
  
  if(definition.getScope() == BeanScope.SINGLETON) {
   definition.setInstance(instance);
   }
   
   definition.setResolving(false);
   return type.cast(instance);
  }
}
