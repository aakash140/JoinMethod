package com.java.thread.join;

class NewThread implements Runnable {

	String name; // name of thread
	Thread t;

	NewThread(String threadname) {
		name = threadname;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		t.start(); // Start the thread
	}
	// This is the entry point for thread.

	@Override
	public void run() {
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println(name + ": " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println(name + " interrupted.");
		}
		System.out.println(name + " exiting.");
	}
}

public class DemoJoin {

	public static void main(String args[]) {
		NewThread ob1 = new NewThread("One");
		NewThread ob2 = new NewThread("Two");
		NewThread ob3 = new NewThread("Three");
		System.out.println("Thread One is alive: " + ob1.t.isAlive());
		System.out.println("Thread Two is alive: " + ob2.t.isAlive());
		System.out.println("Thread Three is alive: " + ob3.t.isAlive());
		// wait for threads to finish
		try {
			System.out.println("Waiting for threads to finish.");

			if (ob1.t.isAlive()) {
				System.out.println(Thread.currentThread() + " will join after ob1.t is finished");
				ob1.t.join();
			} else
				System.out.println("ob1.t is already finished");

			if (ob2.t.isAlive()) {
				System.out.println(Thread.currentThread() + " will join after ob2.t is finished");
				ob2.t.join();
			} else
				System.out.println("ob2.t is already finished.No need to call join on this.");

			if (ob3.t.isAlive()) {
				System.out.println(Thread.currentThread() + "will join after ob3.t is finished");
				ob3.t.join();
			} else
				System.out.println("ob3.t is already finished.No need to call join on this.");
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		System.out.println("Main thread is alive: " + Thread.currentThread().isAlive());
		System.out.println("Main thread exiting.");
	}
}