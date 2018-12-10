package com.example.wangwei.tinyspring.aop;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.MethodProxy;

public interface MethodInterceptor extends Callback {

    public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args,
                            MethodProxy proxy) throws Throwable;
}
