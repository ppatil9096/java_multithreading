package com.ppp.multithreading;

public class VolatileDemo {
    private volatile static int MY_INT = 0;
    // change here to test volatile nature of this variable

    private static class Reader extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while (local_value < 5) {
                if (local_value != MY_INT) {
                    System.out.println("Got Change for MY_INT : " + MY_INT);
                    local_value = MY_INT;
                }
            }
        }
    }

    static class Writer extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while (MY_INT < 5) {
                System.out.println("Incrementing MY_INT to " + (local_value + 1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Reader().start();
        new Writer().start();
    }
}


/* without volatile o/p

Incrementing MY_INT to 1
Incrementing MY_INT to 2
Incrementing MY_INT to 3
Incrementing MY_INT to 4
Incrementing MY_INT to 5
*/


/* using volatile o/p

Incrementing MY_INT to 1
Got Change for MY_INT : 1
Incrementing MY_INT to 2
Got Change for MY_INT : 2
Incrementing MY_INT to 3
Got Change for MY_INT : 3
Incrementing MY_INT to 4
Got Change for MY_INT : 4
Incrementing MY_INT to 5
Got Change for MY_INT : 5
*/
