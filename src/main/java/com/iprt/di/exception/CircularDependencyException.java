package com.iprt.di.exception;

public class CircularDependencyException extends DIException {
 public CircularDependencyException(String message) {
  super(message);
  }
}
