package ru.multithreads.decription;

public class Example {
    public static void main(String... arg) throws InterruptedException {

        Thread th1 = new Thread(new Car());
        th1.start();

        th1.join();

        System.out.println("Работа программы завершена");
    }
}

class Car implements Runnable{
    @Override
    public void run() {
        for (int k=0; k<10000; k++){
            System.out.println(k);
        }
    }
}