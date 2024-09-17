package com.example.virtualthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PinnedTest {

	private final ReentrantLock reentrantLock = new ReentrantLock();

	private final Runnable skip_pinned = new Runnable() {
		@Override
		public void run() {
			reentrantLock.lock();
			System.out.println(Thread.currentThread());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}finally {
				reentrantLock.unlock();
			}
		}
	};

	private final Runnable pinned = new Runnable() {
		@Override
		public void run() {
			synchronized (this){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	};

	public static void main(String[] args) {
		ThreadFactory factory = Thread.ofVirtual().name("test-", 0).factory();
		long startTime = System.currentTimeMillis();
		try(ExecutorService service = Executors.newThreadPerTaskExecutor(factory)){
			for(int i = 0 ; i < 100; i++ ){
				PinnedTest test = new PinnedTest();
				// service.submit(test.pinned);
				service.submit(test.pinned);
			}
		}
		System.out.println("time  " + (System.currentTimeMillis() - startTime));
	}
}
