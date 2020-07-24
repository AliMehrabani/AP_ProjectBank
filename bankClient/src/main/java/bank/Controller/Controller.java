package bank.Controller;

import bank.Model.Account;
import bank.Model.Receipt;
import bank.Model.Token;
import bank.View.BankServer;

public class Controller {
    public static String giveTokenToAccount(String username) {
        Account account = AccountController.getAccountWithUserName(username);
        Token tokenPrime = null;
        for (Token token : BankServer.onlineUsers.keySet()) {
            if (BankServer.onlineUsers.get(token).equals(account)) {
                tokenPrime = token;
            }
        }
        if (tokenPrime != null) {
            BankServer.onlineUsers.remove(tokenPrime, account);
        }
        Token token = new Token(account);
        BankServer.onlineUsers.put(token, account);
        return token.getTokenId();
    }

    public static String generateToken() {
        return RandomString.getAlphaNumericString(20);
    }

    public static void payThisReceipt(String receiptID, String bankToken) {
        Account account = BankServer.onlineUsers.get(TokenController.getTokenByTokenID(bankToken));
        Receipt receipt = ReceiptController.getReceiptWithID(receiptID, bankToken);
        if (receipt.getReceiptType().equals("withdraw")) {
            account.setBalance(account.getBalance() - receipt.getMoney());
        } else if (receipt.getReceiptType().equals("deposit")) {
            account.setBalance(account.getBalance() + receipt.getMoney());
        } else if (receipt.getReceiptType().equals("move")) {
            Account desAccount = AccountController.getAccountWithID(String.valueOf(receipt.getDestinationID()));
            account.setBalance(account.getBalance() - receipt.getMoney());
            desAccount.setBalance(desAccount.getBalance() + receipt.getMoney());
        }
        receipt.setDone(true);

    }

    public static void exitFromBankWithToken(String bankToken) {
        try{
            BankServer.onlineUsers.remove(TokenController.getTokenByTokenID(bankToken));
        }catch (Exception e){

        }
    }
}

class RandomString {

    // function to generate a random string of length n
    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
