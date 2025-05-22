package com.capgemini.complaint_management_system.entities;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import redis.embedded.RedisServer;

@Configuration
public class RedisConfig {

	private RedisServer redisServer;
	
	@PostConstruct
	public void startRedis() throws IOException{
		redisServer = new RedisServer(6379);
		redisServer.start();
		System.out.println("Embedded Redis Server on 6379");		
	}
	
	@PreDestroy
	public void stopRedis() {  
		if(redisServer != null) {
			redisServer.stop();
			System.out.println("Embedded server stopped");
		}
	}
	
}
