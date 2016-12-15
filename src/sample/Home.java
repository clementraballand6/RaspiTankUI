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

        this.handleArrows();
    }

    public void move(String direction) throws Exception {
        Http.get("direction/" + direction.toLowerCase());
        System.out.println(direction);
    }

    public void handleArrows(){
        this.forward.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        move("forward");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.backward.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        move("backward");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.left.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        move("left");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.right.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        move("right");
                    } catch (Exception e) {
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
                this.move(keyName);
            }
            if ("Left".equals(keyName)) {
                this.move(keyName);
            }
            if ("Up".equals(keyName)) {
                this.move(keyName);
            }
            if ("Down".equals(keyName)) {
                this.move(keyName);
            }
        }

        this.lastKey = keyName;

    }

    public void keyReleased(KeyEvent keyEvent) throws Exception {
        this.lastKey = "";
        System.out.println("stop");
        Http.get("direction/stop");
    }

    public void stopMove(MouseEvent mouseEvent) throws Exception {
        this.move("stop");
    }
}
