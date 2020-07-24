package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private String bankToken;
    private String token;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String type;

    public Client() {
        this.bankToken = "";
        this.token = "";
        this.type = "guest";
        this.socket = null;
        this.dataInputStream = null;
        this.dataOutputStream = null;
        View.setClient(this);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run() {
        try {
            this.socket = new Socket("127.0.0.1", 1212);
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            View.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isThereAnyAccountWithUsernameInBank(String username) {
        try {
            dataOutputStream.writeUTF(("isThereAnyAccountWithUsernameInBank"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((username));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void createAccountInBank(String message) { // not fucking complete
        try {
            dataOutputStream.writeUTF(("createAccountInBank"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((message));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPasswordCorrectForBankAccount(String password, String username) {
        try {
            dataOutputStream.writeUTF(("isPasswordCorrectForBankAccount"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((username + "!@" + password));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getTokenInBank(String username) {
        try {
            dataOutputStream.writeUTF(("getTokenInBank"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((username));
            dataOutputStream.flush();
            bankToken = (dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isTokenExpired() {
        try {
            dataOutputStream.writeUTF(("isTokenExpired"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String[] getBankAccountInformation() {
        try {
            dataOutputStream.writeUTF(("getBankAccountInformation"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            return response.split("!@");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isThereAnyBankAccountWithID(String id) {
        try {
            dataOutputStream.writeUTF(("isThereAnyBankAccountWithID"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((id));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void createReceipt(String type, String money, String source, String destination, String description) {
        try {
            String message = type + "!@" + money + "!@" + source + "!@" + destination + "!@" + description + "!@" + bankToken;
            dataOutputStream.writeUTF(("createReceipt"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((message));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isThereAnyReceiptWithID(String receiptID) {
        try {
            dataOutputStream.writeUTF(("isThereAnyReceiptWithID"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String[] getReceiptAndAccountDetailForPay(String receiptID) {
        try {
            dataOutputStream.writeUTF(("getReceiptAndAccountDetailForPay"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            return response.split("!@");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void payThisReceipt(String receiptID) {
        try {
            dataOutputStream.writeUTF(("payThisReceipt"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            System.out.println("response in client " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getReceiptsWithGivenType(String type) {
        try {
            dataOutputStream.writeUTF(("getReceiptsWithGivenType"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((type + "!@" + bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            System.out.println("bug respose : " + response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getReceiptDetailsWithID(String receiptID) {
        try {
            dataOutputStream.writeUTF(("getReceiptDetailsWithID"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void exitFromBank() {
        try {
            dataOutputStream.writeUTF(("exitFromBank"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF((bankToken));
            dataOutputStream.flush();
            String response = (dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
