package com.NotificationRequest.configuration;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class NotificationConfig {

	    @Bean(name = "normal")
	    public RateLimiter normalLimiter(){
	    	 return  RateLimiter.create(1,10,TimeUnit.SECONDS);
	    }
	    
	    @Bean(name = "premium")
	    public RateLimiter premiumLimiter(){
	        return RateLimiter.create(1,10,TimeUnit.SECONDS);
	    }    
}
