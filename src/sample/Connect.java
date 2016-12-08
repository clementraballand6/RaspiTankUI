package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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
    public TextField ip;
    public Label credentialsFailed;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        credentialsFailed.setVisible(false);
        Http.API_PATH = this.ip.getText();
        this.log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    try {
//                        if(isAuth()){
                        if(true){
                            try {
                                Main.go("home");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            credentialsFailed.setVisible(true);
                            password.setText("");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean isAuth() throws Exception {
        return Http.auth(this.login.getText(), this.password.getText());
    }

    public void handleIpChanged(KeyEvent keyEvent) {
        Http.API_PATH = this.ip.getText();
    }
}
