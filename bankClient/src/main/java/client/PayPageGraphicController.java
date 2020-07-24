package client;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PayPageGraphicController implements Initializable {
    public ImageView goBackToMainMenuButton;
    public Label alertLabel;
    public Button payButton;
    public TextField receiptIDTextField;

    public void pay(MouseEvent event) {
        alertLabel.setTextFill(Color.RED);
        alertLabel.setText("");
        boolean errorFound = false;
        if (View.client.isTokenExpired()) {
            errorFound = true;
            goToSpecificPage("getToken", event);
            return;
        }
        if(!View.client.isThereAnyReceiptWithID(receiptIDTextField.getText())){
            alertLabel.setText("invalid receipt id");
            alertLabel.setVisible(true);
            return;
        }
        String[] receiptDetails = View.client.getReceiptAndAccountDetailForPay(receiptIDTextField.getText());
        if(receiptDetails[2].equals("true")){
            alertLabel.setText("receipt is paid before");
            alertLabel.setVisible(true);
            return;
        }
        double aMoney = Double.parseDouble(receiptDetails[1]);
        double rMoney = Double.parseDouble(receiptDetails[0]);
        if(rMoney > aMoney){
            alertLabel.setText("source account does not have enough money");
            alertLabel.setVisible(true);
            return;
        }

        View.client.payThisReceipt(receiptIDTextField.getText());
        restartPage();
    }

    public void goBackToMainMenu(MouseEvent mouseEvent) {
        goToSpecificPage("bankMenu", mouseEvent);
    }

    private void goToSpecificPage(String pageName, MouseEvent mouseEvent) {
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

    private void restartPage() {
        receiptIDTextField.clear();
        alertLabel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restartPage();
    }
}
