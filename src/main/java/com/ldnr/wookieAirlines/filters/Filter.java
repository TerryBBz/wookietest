package com.ldnr.wookieAirlines.filters;

public interface Filter<T> {

   T getStatus();
  void setStatus(T status) ;
    
}