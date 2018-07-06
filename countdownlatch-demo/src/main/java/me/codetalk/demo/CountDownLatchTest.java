package me.codetalk.demo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by guobiao.xu on 2018/7/6.
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        long ts1 = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(4);

        // start task
        System.out.println("Start tasks...");
        new Thread(new SleepTask(1000, latch)).start();
        new Thread(new SleepTask(2000, latch)).start();
        new Thread(new SleepTask(5000, latch)).start();
        new Thread(new SleepTask(3000, latch)).start();

        latch.await();

        long ts2 = System.currentTimeMillis();

        System.out.println(Thread.currentThread() + " continues after " + (ts2 - ts1) + "ms");
    }

}

class SleepTask implements Runnable {

    long millis;                // sleep time
    CountDownLatch latch;       // latch

    SleepTask(long millis, CountDownLatch latch) {
        this.millis = millis;
        this.latch = latch;
    }

    public void run() {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }

        latch.countDown();
    }

}

