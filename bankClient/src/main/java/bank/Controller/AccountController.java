package bank.Controller;
import static bank.Controller.DataBase.*;
import bank.Model.Account;
import bank.Model.Token;
import bank.View.BankServer;

public class AccountController {
    public static Account getAccountWithUserName(String username){
        for (Account account : allAccounts) {
            if(account.getUsername().equalsIgnoreCase(username)) return account;
        }
        return null;
    }

    public static boolean isThereAnyAccountsWithUsername(String username){
        for (Account account : allAccounts) {
            if(account.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public static double getAccountBalanceWithUsername(String username){
        Account account = getAccountWithUserName(username);
        return account.getBalance();
    }

    public static void createAccount(String[] input) {
        Account account = new Account(input[0] , input[1] , input[2] , input[3]);
        allAccounts.add(account);
    }

    public static long generateAccountID() {
        long first14 = (long) (Math.random() * 100000000000000L);
        long number = 5200000000000000L + first14;
        return number;
    }

    public static boolean isThisPassCorrectForThisAccount(String username, String password) {
        if(!isThereAnyAccountsWithUsername(username)) return false;
        Account account = getAccountWithUserName(username);
        if(!account.getPassword().equals(password)) return false;
        return true;
    }

    public static String getAccountInformation(String bankToken) {
        Account account = BankServer.onlineUsers.get(TokenController.getTokenByTokenID(bankToken));

        return account.getBalance() + "!@" + account.getUsername() + "!@" + account.getAccountId();
    }

    public static String isThereAnyBankAccountWithID(String accountID) {
        long id = Long.parseLong(accountID);
        for (Account account : allAccounts) {
            if(account.getAccountId() == id) return "true";
        }
        return "false";
    }

    public static Account getAccountWithID(String accountID){
        long id = Long.parseLong(accountID);
        for (Account account : allAccounts) {
            if(account.getAccountId() == id) return account;
        }
        return null;
    }
}
