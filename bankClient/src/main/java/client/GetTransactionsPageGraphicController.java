package client;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GetTransactionsPageGraphicController implements Initializable {
    public GridPane receiptInformationPane;
    public Label receiptTypeLabel;
    public Label receiptIDLabel;
    public Label moneyLabel;
    public Label sourceIDLabel;
    public Label destinationIDLabel;
    public Label descriptionLabel;
    public ComboBox receiptTypeComboBox;
    public TextField receiptIDTextField;
    public ImageView downArrow;
    public ImageView goBackToMainMenuButton;
    public Label paidLabel;
    private int receiptsIndex;
    private ArrayList<String> receiptsWithType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        receiptsIndex = 0;
        receiptsWithType = new ArrayList<>();
        receiptTypeComboBox.setValue("-");
        receiptTypeComboBox.getItems().add("-");
        receiptTypeComboBox.getItems().add("destination");
        receiptTypeComboBox.getItems().add("source");
        receiptTypeComboBox.getItems().add("all");
        restartWholePane();
    }

    private void restartWholePane() {
        receiptInformationPane.setVisible(false);
        downArrow.setDisable(true);
        downArrow.setVisible(false);
        receiptIDTextField.clear();
        if (receiptTypeComboBox.getValue().equals("-")) {
            receiptIDTextField.setDisable(false);
        } else {
            receiptIDTextField.setDisable(true);
        }
    }

    public void setReceiptType(ActionEvent event) {
        receiptIDTextField.clear();
        if (receiptTypeComboBox.getValue().equals("-")) {
            receiptIDTextField.setDisable(false);
        } else {
            receiptIDTextField.setDisable(true);
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {

    }

    public void searchForReceipt(MouseEvent mouseEvent) {

        receiptsIndex = 0;
        if (View.client.isTokenExpired()) {
            goToSpecificPage("getToken", mouseEvent);
            return;
        }
        if (receiptTypeComboBox.getValue().equals("-") && receiptIDTextField.getText().equals("")) return;

        if (!receiptTypeComboBox.getValue().equals("-")) {
            restartWholePane();
            String type = (String) receiptTypeComboBox.getValue();
            receiptsWithType.clear();
            String input = View.client.getReceiptsWithGivenType(type);

            if (input == null || input.length() == 0 || input.equals("")) return;

            addToArrayList(input.split("#\\$"));
            setReceiptPaneContent();

        } else if (!receiptIDTextField.getText().equals("")) {

            if (!View.client.isThereAnyReceiptWithID(receiptIDTextField.getText())) {
                restartWholePane();
                return;
            } else {

                String inputs = View.client.getReceiptDetailsWithID(receiptIDTextField.getText());
                restartWholePane();
                if (inputs == null || inputs.length() == 0 || inputs.equals("")) return;
                String[] input = inputs.split("!@");
                receiptInformationPane.setVisible(true);
                receiptTypeLabel.setText(input[0]);
                moneyLabel.setText(input[1]);
                sourceIDLabel.setText(input[2]);
                destinationIDLabel.setText(input[3]);
                descriptionLabel.setText(input[4]);
                receiptIDLabel.setText(input[5]);
                if(input[6].equals("true")){
                    paidLabel.setText("yes");
                }else{
                    paidLabel.setText("no");
                }
            }
        }
    }

    private void setReceiptPaneContent() {
        if (receiptsWithType.size() == 0) return;
        restartWholePane();
        if (receiptsWithType.size() > 1) {
            downArrow.setVisible(true);
            downArrow.setDisable(false);
        }
        String[] input = receiptsWithType.get(receiptsIndex).split("!@");
        for (String s : input) {
            System.out.println("input : " + s);
        }
        receiptInformationPane.setVisible(true);
        receiptTypeLabel.setText(input[0]);
        moneyLabel.setText(input[1]);
        sourceIDLabel.setText(input[2]);
        destinationIDLabel.setText(input[3]);
        descriptionLabel.setText(input[4]);
        receiptIDLabel.setText(input[5]);
        if(input[6].equals("true")){
            paidLabel.setText("yes");
        }else{
            paidLabel.setText("no");
        }
    }

    private void addToArrayList(String[] input) {
        receiptsWithType.clear();
        for (String s : input) {
            receiptsWithType.add(s);
        }
    }

    public void seeMoreReceipts(MouseEvent mouseEvent) {
        if (receiptsIndex == receiptsWithType.size() - 1) {
            receiptsIndex = 0;
        } else {
            receiptsIndex++;
        }
        setReceiptPaneContent();
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


    public void goBackToMainMenu(MouseEvent mouseEvent) {
        goToSpecificPage("bankMenu", mouseEvent);
    }


}
