package com.space.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import com.space.entity.User;

public class CASDemo {

	public static void main(String[] args) {

		AtomicInteger atomicInteger = new AtomicInteger(5);
//		atomicInteger.getAndIncrement();
		User z3 = new User("z3", 22);
		User li4 = new User("li4", 25);

		AtomicReference<User> atomicReference = new AtomicReference<User>();
		atomicReference.set(z3);
//		System.out.println(atomicReference.compareAndSet(z3, li4));


		AtomicStampedReference<Integer> asr = new AtomicStampedReference<Integer>(100, 1);

		new Thread(() -> {
			int stamp = asr.getStamp();
			System.out.println(Thread.currentThread().getName() + " \t stamp:" + stamp);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			asr.compareAndSet(100, 101, asr.getStamp(), asr.getStamp() + 1 );
			System.out.println(Thread.currentThread().getName() + " \t stamp:" + asr.getStamp());
			asr.compareAndSet(101, 100, asr.getStamp(), asr.getStamp() + 1 );
			System.out.println(Thread.currentThread().getName() + " \t stamp:" + asr.getStamp());
		}).start();

		new Thread(() -> {
			int stamp = asr.getStamp();
			System.out.println(Thread.currentThread().getName() + " \t stamp:" + stamp);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean b = asr.compareAndSet(100, 2019, stamp, stamp + 1);
			System.out.println(b); // false
			System.out.println(asr.getReference()); // 100
		}).start();

	}

}
