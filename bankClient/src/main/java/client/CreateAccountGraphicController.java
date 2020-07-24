package client;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateAccountGraphicController implements Initializable {
    public Label goGetTokenButton;
    public Button createAccountButton;
    public Label lNameLabel;
    public TextField lastNameReg;
    public Label fNameLabel;
    public TextField firstNameReg;
    public Label rePassLabel;
    public PasswordField rePassReg;
    public Label passLabel;
    public PasswordField passReg;
    public Label userLabel;
    public TextField usernameReg;
    public Label usernameNotAvailableLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }

    public void goGetTokenFxml(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        Pane getToken = null;
        try {
            getToken = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/getToken.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(getToken);
        stage.setScene(scene);
        stage.show();
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        reset();

        if (mouseEvent.getSource().equals(createAccountButton)) {
           boolean booleans = createAccount();
            if (!booleans) {
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    Scene scene = ((Node) mouseEvent.getSource()).getScene();
                    Pane getToken = null;
                    try {
                        getToken = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/getToken.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    scene.setRoot(getToken);
                    stage.setScene(scene);
                    stage.show();
            }
        }
        if (mouseEvent.getSource().equals(usernameReg)) {
            usernameReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userLabel.setVisible(true);
            userLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(passReg)) {
            passReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passLabel.setVisible(true);
            passLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(rePassReg)) {
            rePassReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassLabel.setVisible(true);
            rePassLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(firstNameReg)) {
            firstNameReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            fNameLabel.setVisible(true);
            fNameLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(lastNameReg)) {
            lastNameReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            lNameLabel.setVisible(true);
            lNameLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
    }

    private void reset() {
        usernameNotAvailableLabel.setVisible(false);
        if (usernameReg.getText().equals("")) {
            userLabel.setVisible(false);
        }
        if (passReg.getText().equals("")) {
            passLabel.setVisible(false);
        }
        if (rePassReg.getText().equals("")) {
            rePassLabel.setVisible(false);
        }
        if (firstNameReg.getText().equals("")) {
            fNameLabel.setVisible(false);
        }
        if (lastNameReg.getText().equals("")) {
            lNameLabel.setVisible(false);
        }
        userLabel.setTextFill(Color.valueOf("#a9a9a9"));
        passLabel.setTextFill(Color.valueOf("#a9a9a9"));
        rePassLabel.setTextFill(Color.valueOf("#a9a9a9"));
        fNameLabel.setTextFill(Color.valueOf("#a9a9a9"));
        lNameLabel.setTextFill(Color.valueOf("#a9a9a9"));
        usernameReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        passReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        rePassReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        firstNameReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        lastNameReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
    }

    private boolean createAccount() {
        boolean errorFound = false;

        if (View.client.isThereAnyAccountWithUsernameInBank(usernameReg.getText())) {
            usernameNotAvailableLabel.setVisible(true);
            usernameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!usernameReg.getText().matches("\\w+")) {
            usernameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passReg.getText().equals(rePassReg.getText())) {
            passReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passLabel.setTextFill(Color.valueOf("#fb3449"));
            rePassReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passReg.getText().matches("\\w+")) {
            passReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!rePassReg.getText().matches("\\w+")) {
            rePassReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!firstNameReg.getText().matches("[a-zA-Z]+")) {
            firstNameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            fNameLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!lastNameReg.getText().matches("[a-zA-Z]+")) {
            lastNameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            lNameLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }

        if (!errorFound) {
            View.client.createAccountInBank(usernameReg.getText() + "!@" + passReg.getText() + "!@" + firstNameReg.getText() + "!@" + lastNameReg.getText());
        }

        return errorFound;
    }

}
