package com.example.virtualthread.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Schduler {

	@Scheduled(fixedRate = 1000, scheduler = "threadPoolTaskScheduler")
	public void SimpleScheduler(){
		log.info(Thread.currentThread() + "SimpleScheduler");
	}

	@Scheduled(fixedRate = 1000, scheduler = "simpleAsyncTaskScheduler")
	public void VirtualScheduler(){
		log.info(Thread.currentThread() + "VirtualScheduler");
	}
}
