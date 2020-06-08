package com.javarefresher.threads;

/**
 * Two shoppers adding items to a shared notepad
 */

class ShopperSyncMethod extends Thread {

    static int garlicCount = 0;

    public static synchronized void addGarlic(){
        garlicCount++;
    }

    public void run() {
        for (int i=0; i<10_000_000; i++)
            addGarlic();
    }
}

//Wrong usage of synchronised method. Lock is at object level instead of class
// and class level variable is not protected
class ShopperSyncMethodWrong extends Thread {

    static int garlicCount = 0;

    public synchronized void addGarlic(){
        garlicCount++;
    }

    public void run() {
        for (int i=0; i<10_000_000; i++)
            addGarlic();
    }
}

public class SynchronizedMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new ShopperSyncMethodWrong();
        Thread olivia = new ShopperSyncMethodWrong();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + ShopperSyncMethodWrong.garlicCount + " garlic.");
    }
}