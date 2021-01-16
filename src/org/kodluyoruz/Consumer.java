package org.kodluyoruz;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {
    private int id;
    private Semaphore semaphoreTable;
    private static Table[] tables;

    public Consumer(int id, Semaphore sem, Table[] tables) {
        this.id = id;
        this.semaphoreTable = sem;
        Consumer.tables = tables;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            semaphoreTable.acquire();
            for(int i=0;i<5;i++){
                if(tables[i].getStatus()=="Empty"){
                    tables[i].setCustomerID(this.id);
                    tables[i].setStatus("Sitted");
                    System.out.println("Consumer " + getid() + " is sitting at the table "+ tables[i].getNo());
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphoreTable.release();
    }
}
