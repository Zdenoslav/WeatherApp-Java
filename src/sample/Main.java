package sample;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root, 1175, 800));
       // stopping the window to be resized for the user
        primaryStage.setResizable(false);
        root.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
