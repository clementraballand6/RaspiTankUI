package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Connect implements Initializable {

    @FXML
    public PasswordField password;
    public TextField login;
    @FXML
    public Button log;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {



        this.log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(isAuth()){
                        try {
                            Main.go("home");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private boolean isAuth(){
        return true;
//        return this.login.getText().equals("admin") && this.password.getText().equals("root1234");
    }
}
