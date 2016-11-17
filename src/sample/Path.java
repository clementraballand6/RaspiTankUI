package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Path implements Initializable {

    public ChoiceBox action;
    public TextField time;
    public Button add;
    public Button remove;
    public Button edit;
    public ObservableList<Action> items = FXCollections.observableArrayList();
    public ArrayList<Action> actions = new ArrayList<Action>();
    public ListView<Action> list;
    public Button down;
    public Button up;
    public Button viewPath;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        this.list.setItems(items);
        this.list.setCellFactory(param -> new ListCell<Action>() {
            @Override
            protected void updateItem(Action item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.action + " during " + item.duration + " second(s)");
                }
            }
        });

        this.action.setItems(FXCollections.observableArrayList(
                "Forward", "Backward", "Left", "Right"
        ));
        this.action.getSelectionModel().selectFirst();

        this.initHandlers();
        this.checkFields();
    }

    public void editAction(int index, Action action) {
        items.set(index, action);
    }

    private void handleAddAction() {
        this.add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    System.out.println(list.getItems().size());
                    Action current = new Action(action.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(time.getText()),list.getItems().size());
                    actions.add(current);
                    items.add(current);
                }
            }
        });
    }

    private void handleSwitchUp() {
        this.up.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    int selectedId = list.getSelectionModel().getSelectedIndex();
                    if (selectedId == 0) {
                        return;
                    }
                    Action currentItem = items.get(selectedId);
                    items.remove(selectedId);
                    items.add(selectedId - 1, currentItem);
                    list.getSelectionModel().select(selectedId - 1);
                }
            }
        });
    }

    private void handleSwitchDown() {
        this.down.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    int selectedId = list.getSelectionModel().getSelectedIndex();
                    if (selectedId == list.getItems().size() - 1) {
                        return;
                    }
                    Action currentItem = items.get(selectedId);
                    items.remove(selectedId);
                    items.add(selectedId + 1, currentItem);
                    list.getSelectionModel().select(selectedId + 1);
                }
            }
        });
    }

    private void handleRemove() {
        this.remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    int selectedId = list.getSelectionModel().getSelectedIndex();
                    items.remove(selectedId);
                }
            }
        });
    }

    private void handleViewPath(){
        this.viewPath.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(list.getItems().size() != 0){
                        try {
                            Main.go("viewPath");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void handleEdit() {
        this.edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

                    Main.context.setEditedAction(items.get(list.getSelectionModel().getSelectedIndex()));
                    Main.context.setEditedActionIndex(list.getSelectionModel().getSelectedIndex());
                    // Load the fxml file and create a new stage for the popup
                    Pane page = null;
                    try {
                        page = FXMLLoader.load(Main.class.getResource("EditDialog.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Edit action");
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.initOwner(Main.stage);
                    Scene scene = new Scene(page, 300, 200);
                    dialogStage.setScene(scene);
                    dialogStage.showAndWait();

                    editAction(Main.context.getEditedActionIndex(), Main.context.getEditedAction());
                }
            }
        });
    }

    private void initHandlers() {
        this.handleAddAction();
        this.handleRemove();
        this.handleSwitchUp();
        this.handleSwitchDown();
        this.handleEdit();
    }

    private void checkFields() {
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
