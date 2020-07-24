package client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GetTokenGraphicController {
    public TextField userLogin;
    public PasswordField passLogin;
    public Button loginButton;
    public Label goToCreateNewAccountButton;

    public void mouseClickedGetToken(MouseEvent mouseEvent) {
        userLogin.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        passLogin.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");

        if (mouseEvent.getSource().equals(userLogin)) {
            userLogin.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        }
        if (mouseEvent.getSource().equals(passLogin)) {
            passLogin.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        }
    }

    public void getToken(MouseEvent mouseEvent) {
        userLogin.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        passLogin.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        boolean errorFound = false;
        if (!userLogin.getText().matches("\\w+")) {
            System.out.println("1");
            userLogin.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            errorFound = true;
        } else if (!View.client.isThereAnyAccountWithUsernameInBank(userLogin.getText())) {
            System.out.println("2");
            userLogin.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            errorFound = true;
        }
        if (!passLogin.getText().matches("\\w+")) {
            System.out.println("3");
            passLogin.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            errorFound = true;
        } else if (!View.client.isPasswordCorrectForBankAccount(passLogin.getText(), userLogin.getText())) {
            System.out.println("4");
            passLogin.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            errorFound = true;
        }
        if (!errorFound) {
            View.client.getTokenInBank(userLogin.getText());
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = ((Button) mouseEvent.getSource()).getScene();
            Pane bankMenu = null;
            try {
                bankMenu = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/bankMenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.setRoot(bankMenu);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void goToCreateAccount(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
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
}
