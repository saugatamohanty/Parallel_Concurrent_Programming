package com.javarefresher.threads;

/**
 * Two shoppers adding items to a shared notepad
 */

class ShopperSyncStmt extends Thread {

    static int garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            synchronized (ShopperSyncStmt.class) {
                garlicCount++;
            }
    }
}

//Synchronized on object and resulting in no lock at all
class ShopperSyncStmtWrg1 extends Thread {

    static int garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            synchronized (this) {
                garlicCount++;
            }
    }
}

//New Object created with increment operation, hence lock is changed. Immutable object used to lock
class ShopperSyncStmtWrg2 extends Thread {

    static Integer garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            synchronized (garlicCount) {
                garlicCount++;
            }
    }
}


class ShopperSyncStmtExp extends Thread {

    static String lock = "Lock";
    static Integer garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            synchronized (lock) {
                garlicCount++;
            }
    }
}

public class SynchronizedStatementDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new ShopperSyncStmtExp();
        Thread olivia = new ShopperSyncStmtExp();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + ShopperSyncStmtExp.garlicCount + " garlic.");
    }
}