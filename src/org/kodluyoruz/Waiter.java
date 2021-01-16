package org.kodluyoruz;

import java.util.concurrent.Semaphore;

public class Waiter implements Runnable {
    private int id;
    private Semaphore semaphoreTable;
    private static Table[] tables;

    public Waiter(int id, Semaphore semt, Table[]tables) {
        this.id = id;
        this.semaphoreTable = semt;
        Waiter.tables = tables;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        boolean working;
        while(true){

            try {
                Thread.sleep(2000);
                semaphoreTable.acquire();

                working = false;
                if(!working){
                    for(int i=0;i<5;i++){
                        if(tables[i].getStatus()=="Prepared"){
                            tables[i].setStatus("Delivered");
                            System.out.println("Waiter "+ getid()+ " is delivering order of Table "+ tables[i].getNo());
                            System.out.flush();
                            working=true;
                            break;
                        }
                    }
                }
                if(!working){

                    for(int i=0;i<5;i++){
                        if(tables[i].getStatus()=="Ordered"){
                            tables[i].setStatus("Notified");
                            System.out.println("Waiter "+ getid()+ " is notifying the order of Table "+ tables[i].getNo()+ " to kitchen.");
                            System.out.flush();
                            working=true;
                            break;
                        }
                    }
                }
                if(!working){
                    for(int i=0;i<5;i++){
                        if(tables[i].getStatus()=="Sitted"){
                            tables[i].setWaiterID(this.id);
                            tables[i].setStatus("Ordered");
                            System.out.println("Waiter " +getid() + " is getting order of table "+ tables[i].getNo());
                            System.out.flush();
                            working=true;
                            break;
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphoreTable.release();
        }

    }
}
