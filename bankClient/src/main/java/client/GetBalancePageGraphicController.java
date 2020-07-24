package client;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GetBalancePageGraphicController implements Initializable {
    public Label balanceLabel;
    public Label usernameLabel;
    public Label accountNumberLabel;
    public ImageView goBackToMainMenuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabels();
    }

    public void goBackToMainMenu(MouseEvent mouseEvent) {
        boolean b = View.client.isTokenExpired();
        System.out.println(b);
        if(b){
            goToSpecificPage("getToken" , mouseEvent);
        }else{
            goToSpecificPage("bankMenu" , mouseEvent);
        }
    }


    private void setLabels() {
        String[] data =  View.client.getBankAccountInformation();
        for (String datum : data) {
            System.out.println(datum);
        }
        balanceLabel.setText(data[0]);
        usernameLabel.setText(data[1]);
        accountNumberLabel.setText(data[2]);
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
}
