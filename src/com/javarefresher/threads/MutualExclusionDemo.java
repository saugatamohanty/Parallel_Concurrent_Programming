package com.javarefresher.threads;

import java.util.concurrent.locks.*;

/**
 * Two shoppers adding items to a shared notepad
 */

class ShopperMutex extends Thread {

    static Lock pencil = new ReentrantLock();
    static int garlicCount = 0;


    public void run() {
        pencil.lock();
        for (int i=0; i<5; i++) {
            garlicCount++;
            System.out.println(Thread.currentThread().getName() + " is thinking");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pencil.unlock();
    }
}

//Enhanced use of Mutex lock
class ShopperMutexEn extends Thread {

    static Lock pencil = new ReentrantLock();
    static int garlicCount = 0;


    public void run() {
        for (int i=0; i<5; i++) {
            pencil.lock();
            garlicCount++;
            pencil.unlock();
            System.out.println(Thread.currentThread().getName() + " is thinking");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class MutualExclusionDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new ShopperMutexEn();
        Thread olivia = new ShopperMutexEn();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + ShopperMutex.garlicCount + " garlic.");
    }
}