package bank.Model;

import bank.Controller.Controller;

import java.io.Serializable;

public class Receipt {
    private Account account;
    private boolean done;
    private String receiptType;
    private double money;
    private long sourceID;
    private long destinationID;
    private String description;
    private String receiptID;

    public Receipt(Account account, String receiptType, double money, long sourceID, long destinationID, String description) {
        this.account = account;
        this.done = false;
        this.receiptType = receiptType;
        this.money = money;
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.description = description;
        receiptID = Controller.generateToken();
        account.addReceipt(this);
        System.out.println(this.receiptID);
    }


    @Override
    public String toString() {
        return receiptType + "!@" +
                +money +
                "!@" + sourceID +
                "!@" + destinationID +
                "!@" + description +
                "!@" + receiptID +
                "!@" + done;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getSourceID() {
        return sourceID;
    }

    public void setSourceID(long sourceID) {
        this.sourceID = sourceID;
    }

    public long getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(long destinationID) {
        this.destinationID = destinationID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
