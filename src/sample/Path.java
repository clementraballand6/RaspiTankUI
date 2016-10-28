package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Path implements Initializable {

    public ChoiceBox action;
    public TextField time;
    public Button add;
    public ListView list;
    public Button remove;
    public Button edit;
    public ObservableList<String> items = FXCollections.observableArrayList();
    public ArrayList<Action> actions = new ArrayList<Action>();
    public Button down;
    public Button up;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        this.action.setItems(FXCollections.observableArrayList(
                "Forward", "Backward", "Left", "Right"
        ));
        this.action.getSelectionModel().selectFirst();

        this.initHandlers();
        this.checkFields();
    }

    private void handleAddAction (){
        this.list.setItems(items);
        this.add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    Action current = new Action(action.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(time.getText()));
                    actions.add(current);
                    items.add(current.toString());
                }
            }
        });
    }

    private void handleSwitchUp (){
        this.up.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    int selectedId = list.getSelectionModel().getSelectedIndex();
                    String selectedText = list.getSelectionModel().getSelectedItem().toString();
                    if(selectedId == 0){
                        return;
                    }
                    // switch in obj array
                    Action action = actions.get(selectedId);
                    actions.remove(selectedId);
                    actions.add(selectedId - 1, action);

                    // switch in listView
                    items.remove(selectedId);
                    items.add(selectedId - 1, selectedText);
                    list.getSelectionModel().select(selectedId - 1);
                }
            }
        });
    }

    private void handleSwitchDown (){
        this.down.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    int selectedId = list.getSelectionModel().getSelectedIndex();
                    String selectedText = list.getSelectionModel().getSelectedItem().toString();
                    if(selectedId == list.getItems().size() - 1){
                        return;
                    }
                    items.remove(selectedId);
                    items.add(selectedId + 1, selectedText);
                    list.getSelectionModel().select(selectedId + 1);
                }
            }
        });
    }

    private void handleRemove (){
        this.remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    int selectedId = list.getSelectionModel().getSelectedIndex();
                    items.remove(selectedId);
                    actions.remove(selectedId);
                }
            }
        });
    }

    private void initHandlers(){
        this.handleAddAction();
        this.handleRemove();
        this.handleSwitchUp();
        this.handleSwitchDown();
    }

    private void checkFields (){
        this.time.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    time.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
