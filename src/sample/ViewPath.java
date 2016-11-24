package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPath implements Initializable {

    public Pane pathPane;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        int posX = 0;
        int posY = 0;                                                    
        for (int i = 0; i < Main.context.getActionList().size(); i++){
            int lastPosX = posX;
            int lastPosY = posY;
            Action action = Main.context.getActionList().get(i);
            Line line = new Line();
            if(action.action == "forward"){

            }else{
                pathPane.getChildren().add(new Line(0,0,10,10));
            }
        }
    }

}
