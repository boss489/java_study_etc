package com.example.virtualthread.config;

import org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder;
import org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheulerConfig {

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadNamePrefix("simple-thread-pool-");
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}

	@Primary
	@Bean
	public SimpleAsyncTaskScheduler simpleAsyncTaskScheduler(SimpleAsyncTaskSchedulerBuilder Builder){
		return Builder.build();
	}
}
