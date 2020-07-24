package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateReceiptPageGraphicController implements Initializable {
    public TextField moneyTextField;
    public TextField sourceTextField;
    public TextField destinationTextField;
    public TextField descriptionTextField;
    public ComboBox receiptTypeComboBox;
    public Button createReceiptButton;
    public ImageView goBackToMainMenuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        receiptTypeComboBox.setValue("move");
        receiptTypeComboBox.getItems().add("move");
        receiptTypeComboBox.getItems().add("deposit");
        receiptTypeComboBox.getItems().add("withdraw");
        restartInsideOfTextFields();
        setTextFieldsSit();
    }

    private void restartInsideOfTextFields() {
        moneyTextField.clear();
        sourceTextField.clear();
        descriptionTextField.clear();
        destinationTextField.clear();
        moneyTextField.setStyle("-fx-border-color: darkgray;\" + \"-fx-border-radius: 8;\" + \"-fx-background-radius: 8");
        sourceTextField.setStyle("-fx-border-color: darkgray;\" + \"-fx-border-radius: 8;\" + \"-fx-background-radius: 8");
        descriptionTextField.setStyle("-fx-border-color: darkgray;\" + \"-fx-border-radius: 8;\" + \"-fx-background-radius: 8");
        destinationTextField.setStyle("-fx-border-color: darkgray;\" + \"-fx-border-radius: 8;\" + \"-fx-background-radius: 8");

    }

    public void setReceiptType(ActionEvent event) {
        setTextFieldsSit();
    }

    private void setTextFieldsSit() {
        String type = (String) receiptTypeComboBox.getValue();
        destinationTextField.setDisable(true);
        sourceTextField.setDisable(true);
        destinationTextField.clear();
        sourceTextField.clear();
        if (type.equals("move")) {
            destinationTextField.setDisable(false);
            sourceTextField.setDisable(false);
        } else if (type.equals("deposit")) {
            sourceTextField.setText("-");
            destinationTextField.setDisable(false);
        } else if (type.equals("withdraw")) {
            destinationTextField.setText("-");
            sourceTextField.setDisable(false);
        }
    }

    public void createReceipt(ActionEvent event) {
        if (View.client.isTokenExpired()) {
            goToSpecificPage("getToken", event);
            return;
        }
        if (moneyTextField.getText().equals("") || descriptionTextField.getText().equals("")) return;
        boolean errorFound = false;
        String type = (String) receiptTypeComboBox.getValue();
        if (type.equals("move")) {

            if (sourceTextField.getText().equals("") || destinationTextField.getText().equals("")) return;
            if (!sourceTextField.getText().matches("(\\d+)")) {
                sourceTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
            if (!destinationTextField.getText().matches("(\\d+)")) {
                destinationTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
            if (!View.client.isThereAnyBankAccountWithID(sourceTextField.getText())) {
                sourceTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
            if (!View.client.isThereAnyBankAccountWithID(destinationTextField.getText())) {
                destinationTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
            if(sourceTextField.getText().equals(destinationTextField.getText())){
                sourceTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                destinationTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }

        } else if (type.equals("deposit")) {
            if (!destinationTextField.getText().matches("(\\d+)")) {
                destinationTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
            if (destinationTextField.getText().equals("")) return;
            if (!View.client.isThereAnyBankAccountWithID(destinationTextField.getText())) {
                destinationTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
        } else if (type.equals("withdraw")) {
            if (!sourceTextField.getText().matches("(\\d+)")) {
                sourceTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
            if (sourceTextField.getText().equals("")) return;
            if (!View.client.isThereAnyBankAccountWithID(sourceTextField.getText())) {
                sourceTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
                errorFound = true;
            }
        }
        if (!moneyTextField.getText().matches("(\\d+)(\\.\\d+)?")) {
            moneyTextField.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            errorFound = true;
        }

        if (!errorFound) {
            View.client.createReceipt(type, moneyTextField.getText(), sourceTextField.getText(), destinationTextField.getText(), descriptionTextField.getText());
            goToSpecificPage("bankMenu", event);
        }
    }

    public void goBackToMainMenu(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        Pane getToken = null;
        try {
            getToken = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/bankMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(getToken);
        stage.setScene(scene);
        stage.show();
    }

    private void goToSpecificPage(String pageName, ActionEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        Pane getToken = null;
        try {
            getToken = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/" + pageName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(getToken);
        stage.setScene(scene);
        stage.show();
    }

    public void mouseClicked(MouseEvent mouseEvent) {
    }
}
