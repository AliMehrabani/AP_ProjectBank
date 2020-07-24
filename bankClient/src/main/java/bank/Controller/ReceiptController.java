package bank.Controller;

import bank.Model.Account;
import bank.Model.Receipt;
import bank.Model.Token;
import bank.View.BankServer;

import javax.print.DocFlavor;

public class ReceiptController {
    public static void createReceipt(String[] input) {
        try {
            String type = input[0];
            double money = Double.parseDouble(input[1]);
            String description = (input[4]);
            Account account = BankServer.onlineUsers.get(TokenController.getTokenByTokenID(input[5]));

            if (type.equals("move")) {
                long source = Long.parseLong(input[2]);
                long destination = Long.parseLong((input[3]));
                Receipt receipt = new Receipt(account, type, money, source, destination, description);
            } else if (type.equals("deposit")) {
                long destination = Long.parseLong((input[3]));
                Receipt receipt = new Receipt(account, type, money, -1, destination, description);
            } else if (type.equals("withdraw")) {
                long source = Long.parseLong(input[2]);
                Receipt receipt = new Receipt(account, type, money, source, -1, description);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("can not create receipt");
        }
    }

    public static boolean isThereAnyReceiptWithID(String receiptID, String bankToken) {
        Account account = BankServer.onlineUsers.get(TokenController.getTokenByTokenID(bankToken));
        for (Receipt receipt : account.getAllReceipts()) {
            if (receipt.getReceiptID().equals(receiptID)) return true;
        }
        return false;
    }

    public static Receipt getReceiptWithID(String receiptID, String bankToken) {
        Account account = BankServer.onlineUsers.get(TokenController.getTokenByTokenID(bankToken));
        for (Receipt receipt : account.getAllReceipts()) {
            if (receipt.getReceiptID().equals(receiptID)) return receipt;
        }
        return null;
    }


    public static String getReceiptDetails(String receiptID, String bankToken) {
        Receipt receipt = getReceiptWithID(receiptID, bankToken);
        Account account = BankServer.onlineUsers.get(TokenController.getTokenByTokenID(bankToken));
        return receipt.getMoney() + "!@" + account.getBalance() + "!@" + receipt.isDone();
    }

    public static String getReceiptsWithGivenType(String receiptType, String bankToken) {
        Account mainAccount = BankServer.onlineUsers.get(TokenController.getTokenByTokenID(bankToken));
        long mainAccountID = mainAccount.getAccountId();
        String response = "";
        if (receiptType.equals("all")) {
            for (Account account : DataBase.allAccounts) {
                for (Receipt receipt : account.getAllReceipts()) {
                    if (receipt.getDestinationID() == mainAccountID || receipt.getSourceID() == mainAccountID) {
                        response += receipt.toString() + "#$";
                    }
                }
            }
        } else if (receiptType.equals("destination")) {
            for (Account account : DataBase.allAccounts) {
                for (Receipt receipt : account.getAllReceipts()) {
                    if (receipt.getDestinationID() == mainAccountID) {
                        response += receipt.toString() + "#$";
                    }
                }
            }
        } else if (receiptType.equals("source")) {
            for (Account account : DataBase.allAccounts) {
                for (Receipt receipt : account.getAllReceipts()) {
                    if (receipt.getSourceID() == mainAccountID) {
                        response += receipt.toString() + "#$";
                    }
                }
            }
        }
        System.out.println("in controller  "+ response);
        if(response.length() == 0) return response;

        if (response.substring(response.length() - 2).equals("#$"))
            response = response.substring(0, response.length() - 2);
        System.out.println("in controller  "+ response);
        return response;
    }

    public static String getReceiptDetailsWithID(String receiptID, String bankToken) {
        Receipt receipt = getReceiptWithID(receiptID, bankToken);
        return receipt.toString();
    }
}
