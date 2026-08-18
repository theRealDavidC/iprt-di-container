package com.iprt.di.container;

import com.iprt.di.container.BeanScope;

public class BeanDefinition {
 private Class<?> type;
 private BeanScope scope;
 private boolean resolving;
 private Object instance;
 
public BeanDefinition(
       Class<?> type, 
       BeanScope scope) {
       this.type = type;
       this.scope = scope;
       this.resolving = false;
       this.instance = null;
       }
       
public Class<?> getType() {
 return type;
 }
 
public BeanScope getScope() {
 return scope;
 }
 
public boolean isResolving() {
 return resolving;
 }
 
public void setResolving(boolean resolving) {
 this.resolving = resolving;
 }
 
public Object getInstance() {
 return instance;
 }
 
public void setInstance(Object instance) {
 this.instance = instance;
 }
 }
       
