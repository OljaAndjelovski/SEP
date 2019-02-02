package com.ftn.uns.payment_gateway.config;

import com.ftn.uns.payment_gateway.repository.PayPalTokenRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {
        String[] parts = clazz.getName().replace('.', '=').split("=");
        String name = parts[parts.length-1].substring(0,1).toLowerCase() + parts[parts.length-1].substring(1);
        return (T)context.getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {

        // store ApplicationContext reference to access required beans later on
        SpringContext.context = context;
    }
}
