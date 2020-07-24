package bank.Model;

import bank.Controller.AccountController;

import java.util.ArrayList;

public class Account {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private long accountId;
    private double balance;
    private ArrayList<Receipt> allReceipts;
    public Account(String username, String password, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        accountId = AccountController.generateAccountID();
        balance = 1000000.0;
        allReceipts = new ArrayList<>();
    }

    public void addReceipt(Receipt receipt){
        allReceipts.add(receipt);
    }

    public ArrayList<Receipt> getAllReceipts() {
        return allReceipts;
    }

    public void setAllReceipts(ArrayList<Receipt> allReceipts) {
        this.allReceipts = allReceipts;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
