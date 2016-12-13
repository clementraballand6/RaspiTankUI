package sample;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    public Button forward;
    public Button right;
    public Button backward;
    public Button left;
    public Button planPath;
    public Button showPaths;
    public String lastKey = "";

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        this.planPath.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        Main.go("path");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        this.showPaths.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        Main.go("viewPath");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void keyPressed(KeyEvent keyEvent) throws Exception {
        String keyName = keyEvent.getCode().getName();
        if (!this.lastKey.equals(keyName)) {
            if ("Right".equals(keyName)) {
                System.out.println(keyName);
                Http.get("direction/right");
            }
            if ("Left".equals(keyName)) {
                Http.get("direction/left");
                System.out.println(keyName);
            }
            if ("Up".equals(keyName)) {
                Http.get("direction/forward");
                System.out.println(keyName);
            }
            if ("Down".equals(keyName)) {
                Http.get("direction/backward");
                System.out.println(keyName);
            }
        }

        this.lastKey = keyName;

    }

    public void keyReleased(KeyEvent keyEvent) {
        this.lastKey = "";
    }
}
