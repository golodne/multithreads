package ru.multithreads.Valotaile;

import java.sql.SQLOutput;

public class ValotaileExample {

  //  it is very bad, because thWaiter is cashed variable flag
  //  public static boolean flag;

    //if operation is atomicaly - you can use valotile
    public volatile static boolean flag;


    public static void main(String[] args) throws InterruptedException {

       Thread thSignal = new Thread(new Runnable() {
           @Override
           public void run() {

               try {
                   Thread.currentThread().sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               flag = true;
           }
       });

       Thread thWaiter = new Thread(new Runnable() {
           @Override
           public void run() {
                while (!flag);
               System.out.println("Внимание! Изменение флага!");
           }
       });


       thSignal.start();
       thWaiter.start();

       thSignal.join();
       thWaiter.join();

       System.out.println("Работа программы завершена");
    }




}

