package org.kodluyoruz;

public class Table {
    private static int count = 1;

    private int no;
    private int customerID;
    private int waiterID;
    private int chiefID;
    private String status;

    public Table() {
        this.no = count++;
        this.customerID = -1;
        this.status = "Empty";
    }

    public int getChiefID() {
        return chiefID;
    }

    public void setChiefID(int chiefID) {
        this.chiefID = chiefID;
    }

    public int getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(int waiterID) {
        this.waiterID = waiterID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

}
