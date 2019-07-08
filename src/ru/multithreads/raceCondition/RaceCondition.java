package ru.multithreads.raceCondition;

public class RaceCondition {

    public static int counter = 0;

    //with out synchronized - other values not equals 2000000
    public synchronized static void incCounter(){
        counter++;
    }


    public static void main(String... args) throws InterruptedException {

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i<=100000; i++){
                    incCounter();
                }

                System.out.println("Работа потока "+Thread.currentThread().getName()+" завершена ");
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i<=100000; i++){
                    incCounter();
                }

                System.out.println("Работа потока "+Thread.currentThread().getName()+" завершена ");
            }
        });



        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(counter);


    }

}
