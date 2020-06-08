package com.javarefresher.threads;
/**
 * Two shoppers adding garlic and potatoes to a shared notepad
 */

import java.util.concurrent.locks.*;

class ShopperReEntrant extends Thread {

    static int garlicCount, potatoCount = 0;
    static Lock pencil = new ReentrantLock();

    private void addGarlic() {
        pencil.lock();
        garlicCount++;
        pencil.unlock();
    }

    private void addPotato() {
        pencil.lock();
        potatoCount++;
        pencil.unlock();
    }

    public void run() {
        for (int i=0; i<10_000; i++) {
            addGarlic();
            addPotato();
        }
    }
}

class ShopperReEntrantVar1 extends Thread {

    static int garlicCount, potatoCount = 0;
    static ReentrantLock pencil = new ReentrantLock();

    private void addGarlic() {
        pencil.lock();
        System.out.println("Pencil lock hold count" + pencil.getHoldCount());
        garlicCount++;
        pencil.unlock();
    }

    private void addPotato() {
        pencil.lock();
        potatoCount++;
        addGarlic();
        pencil.unlock();
    }

    public void run() {
        for (int i=0; i<10_000; i++) {
            addGarlic();
            addPotato();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new ShopperReEntrantVar1();
        Thread olivia = new ShopperReEntrantVar1();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + ShopperReEntrantVar1.garlicCount + " garlic.");
        System.out.println("We should buy " + ShopperReEntrantVar1.potatoCount + " potatoes.");
    }
}