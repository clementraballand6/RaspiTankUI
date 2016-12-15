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
    public ArrayList<ArrayList<Action>> actions = new ArrayList<ArrayList<Action>>();

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        actionsList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                setList((int) number);
//                setList((int) number2);
            }
        });

        List<Integer> box = new LinkedList<>();

        this.actions = Main.context.allActions;
        for(int i = 0; i < this.actions.size(); i++){
            box.add(i);
        }

        this.actionsList.setItems(FXCollections.observableArrayList(box));
        this.actionsList.getSelectionModel().selectFirst();
        this.setList(0);
    }

    public void setList(int index){
            ArrayList<Action> actionList = new ArrayList<>();
            if(actions.size() == 0){
                return;
            }
            for(int k = 0; k < actions.get(index).size(); k++){
                actionList.add(actions.get(index).get(k));
            }
            ObservableList<Action> actions = FXCollections.observableArrayList(actionList);
            list.setItems(actions);
    }

    public void runPath(MouseEvent mouseEvent) {
        String jsonArray = "[";
        for (int i = 0; i < list.getItems().size(); i++) {
            jsonArray += list.getItems().get(i).toJson();
            if(i!=list.getItems().size()-1){
                jsonArray += ",";
            }
        }
        jsonArray += "]";
        System.out.println(jsonArray);

        try {
            Http.get("runJsonPath/" + jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Main.go("path");
    }
}
