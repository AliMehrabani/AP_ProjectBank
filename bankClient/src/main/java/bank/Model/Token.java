package bank.Model;

import bank.Controller.Controller;

public class Token {
    private Account account;
    private String tokenId;
    private boolean isAlive;
    private int time;

    public Token(Account account) {
        this.account = account;
        isAlive = true;
        time = 3600;
        this.tokenId = Controller.generateToken();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
