package ru.multithreads.synch;

public class DemoSync {

    private static int amount;

    public static void TransformAmount(String account){
        System.out.println("ожидание перевода для потока "+Thread.currentThread().getName());
        synchronized (DemoSync.class){
            System.out.println(Thread.currentThread().getName()+" заблокировал счет");

            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" разблокировал счет");
        }
    }



    public static void main(String... args) throws InterruptedException{

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                TransformAmount("1234");
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                TransformAmount("1234");
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println("Работа программы завершена");

    }
}
