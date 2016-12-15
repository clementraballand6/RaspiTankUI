package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    public static int test;
    public static Context context = new Context();

    public static void go(String stage) throws IOException {
        Main.stage.setTitle(stage);
        Main.stage.setScene(new Scene((Parent) FXMLLoader.load(Main.class.getResource(stage + ".fxml")), 600, 400));
        Main.stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("connect.fxml"));
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("RaspiTank UI");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
