package sample;

import com.google.gson.Gson;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

//import jdk.nashorn.internal.parser.JSONParser;
//import jdk.nashorn.internal.runtime.JSONFunctions;
//import jdk.nashorn.internal.runtime.JSONListAdapter;

public class ViewPath implements Initializable {

    public ChoiceBox actionsList;
    public ListView<Action> list;
    public Action[][] actions;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        Gson g = new Gson();
        this.actions = g.fromJson("[[{\"action\":\"Right\",\"duration\":1},{\"action\":\"Right\",\"duration\":3}],[{\"action\":\"Right\",\"duration\":1},{\"action\":\"Right\",\"duration\":4}]]", Action[][].class);
        List<Integer> box = new LinkedList<>();
        for(int i = 0; i < this.actions.length; i++){
            box.add(i);
        }

        this.actionsList.setItems(FXCollections.observableArrayList(box));
        this.actionsList.getSelectionModel().selectFirst();
        this.setList(0);
        actionsList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                setList((int) number2);
            }
        });
    }

    public void setList(int index){
            ArrayList<Action> actionList = new ArrayList<>();
            for(int k = 0; k < actions[index].length; k++){
                actionList.add(actions[index][k]);
            }
            ObservableList<Action> actions = FXCollections.observableArrayList(actionList);
            list.setItems(actions);
    }
}
