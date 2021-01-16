package org.kodluyoruz;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Restaurant {
    private static Table[] tables;

    public static void main(String[] args) throws InterruptedException {
        Semaphore tableSemaphore = new Semaphore(1,true);

        tables = new Table[5];

        for(int i=0;i<5;i++)
            tables[i] = new Table();

        ArrayList<Thread> threads = new ArrayList<Thread>();

        for(int i=1;i<4;i++){
            Thread waiterThread = new Thread(new Waiter(i,tableSemaphore,tables));
            threads.add(waiterThread);
        }

        for(int i=1;i<3;i++){
            Thread chiefThread = new Thread(new Chief(i,tableSemaphore,tables));
            threads.add(chiefThread);
        }


        int cc=1;
        while(cc<10){
            Thread consumerThread = new Thread(new Consumer(cc,tableSemaphore,tables));
            threads.add(consumerThread);
            cc++;
        }

        for(Thread thread:threads){
            thread.start();
        }
        for(Thread thread:threads){
            thread.join();
        }
        System.out.println("Main thread is terminating");

    }
}
