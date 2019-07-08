package ru.multithreads.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentRantLock {

    private static int amount = 10000;

    public static ReentrantLock locker = new ReentrantLock();

    public static void TransformAmount(int deltSumm) {
        System.out.println("ожидание перевода для потока "+Thread.currentThread().getName());

        try{

            if (locker.tryLock(1000, TimeUnit.MILLISECONDS)){
                System.out.println(Thread.currentThread().getName()+" заблокировал счет");
                //заснуть на 3 сек
                Thread.currentThread().sleep(3000);
                amount = amount + deltSumm;

                System.out.println(Thread.currentThread().getName()+" разблокировал счет");
                locker.unlock();
            } else
                System.out.println("Ошбика захвата ресурса " + Thread.currentThread().getName());

        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("выход из функции TransformAmount " + Thread.currentThread().getName());
    }


    public static void main(String... args) throws InterruptedException{

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                TransformAmount(-6000);
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                TransformAmount(-1500);
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println("Расчет завершен. Amount: " + amount );

    }
}

