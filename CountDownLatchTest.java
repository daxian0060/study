package com.sychronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch测试
 * @author 李
 *
 */
public class CountDownLatchTest implements Runnable{
    public static CountDownLatch count = new CountDownLatch(10);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " -> start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            count.countDown();
        }
        System.out.println(Thread.currentThread().getName() + " -> end");
    }

    public static void main(String[] args) {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(countDownLatchTest);
            list.add(t1);
        }
        list.forEach(o->o.start());
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main 线程 结束");
    }
}
