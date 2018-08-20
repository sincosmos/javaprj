package com.sincosmos.thinkjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSection {
	static void testApproaches(PairManager pm1, PairManager pm2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator pmor1 = new PairManipulator(pm1);
		PairManipulator pmor2 = new PairManipulator(pm2);
		PairChecker pchecker1 = new PairChecker(pm1);
		PairChecker pchecker2 = new PairChecker(pm2);
		exec.execute(pmor1);
		exec.execute(pmor2);
		exec.execute(pchecker1);
		exec.execute(pchecker2);
		exec.shutdown();
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		}catch(InterruptedException e) {
			System.out.println("Sleep interrupted");
		}
		
		System.out.println("pmor1: " + pmor1 + "\npmor2: " + pmor2);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		PairManager pm1 = new PairManager1();
		PairManager pm2 = new PairManager2();
		testApproaches(pm1, pm2);
	}
}
