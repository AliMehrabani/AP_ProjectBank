package client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BankMenuGraphicController {
    public Label goToCreateNewAccountButton;
    public Label createReceiptLabel;
    public Label payLabel;
    public Label getTransactionsLabel;
    public Label getBalanceLabel;


    public void goToCreateAccount(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        Pane createAccount = null;
        try {
            createAccount = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/createAccount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(createAccount);
        stage.setScene(scene);
        stage.show();
    }

    public void createReceiptButton(MouseEvent mouseEvent) {
        if (View.client.isTokenExpired()) {
            goToSpecificPage("getToken", mouseEvent);
        } else {
            goToSpecificPage("createReceiptPage", mouseEvent);
        }
    }

    public void payButton(MouseEvent mouseEvent) {
        if (View.client.isTokenExpired()) {
            goToSpecificPage("getToken", mouseEvent);
        } else {
            goToSpecificPage("payPage", mouseEvent);
        }
    }

    public void getTransactionsButton(MouseEvent mouseEvent) {
        if (View.client.isTokenExpired()) {
            goToSpecificPage("getToken", mouseEvent);
        } else {
            goToSpecificPage("getTransactionsPage", mouseEvent);
        }
    }

    public void getBalanceButton(MouseEvent mouseEvent) {
        boolean b = View.client.isTokenExpired();
        System.out.println(b);
        if (b) {
            goToSpecificPage("getToken", mouseEvent);
        } else {
            goToSpecificPage("getBalancePage", mouseEvent);
        }
    }



    private void goToSpecificPage(String pageName, MouseEvent mouseEvent) {

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        Pane getToken = null;
        try {
            System.out.println(pageName);
            getToken = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/" + pageName + ".fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(getToken);
        stage.setScene(scene);
        stage.show();
    }

}
