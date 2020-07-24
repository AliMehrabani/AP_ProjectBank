package client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

public class View extends Application {
    public static Client client;

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        View.client = client;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane main = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/getToken.fxml"));
        Scene scene = new Scene(main);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            e.consume();
            client.exitFromBank();
            System.exit(0);
        });
    }
    public static void run() {
        View.launch();
    }
}
