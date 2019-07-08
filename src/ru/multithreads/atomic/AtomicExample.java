package ru.multithreads.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

   // public static int counter;
    public static AtomicInteger atCounter = new AtomicInteger(0);


    public static void main(String... args) throws InterruptedException {

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
              for (int i = 1; i<=100000; i++){
                  atCounter.addAndGet(1);
              }
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i<=100000; i++){
                    atCounter.addAndGet(1);
                }
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();
        System.out.println("Работа программы завершена Conter = " + atCounter.get());


    }

}
