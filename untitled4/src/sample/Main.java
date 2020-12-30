package sample;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {


    BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {

        rootLayout = (BorderPane) FXMLLoader.load(getClass().getResource("/sample/fxml/sample.fxml"));
        primaryStage.setTitle("Оценки");
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
        Parent root2 = FXMLLoader.load(getClass().getResource("/sample/fxml/pane.fxml"));
        rootLayout.setCenter(root2);
    }



    public static void main(String[] args) {


        launch(args);
    }
}


