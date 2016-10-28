package sample;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by craballand.cpi2019 on 28/10/2016.
 */
public class EditDialog implements Initializable {

    public static Action action;
    public static ChoiceBox list;
    public static TextField second;
    public static Button close;
    public static Button edit;
    private static int actionId;

    public static void setEditedAction(int actionId, Action action) {
        EditDialog.actionId = actionId;
        EditDialog.action = action;

        EditDialog.list.setItems(FXCollections.observableArrayList(
                "Forward", "Backward", "Left", "Right"
        ));
        EditDialog.second.setText(Integer.toString(action.duration));
        EditDialog.list.getSelectionModel().select(action.action);
    }

    public static void handleClose() {
        EditDialog.close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    EditDialog.close();
                }
            }
        });
    }

    public static void handleEdit() {
        EditDialog.edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    Action newAction = new Action(EditDialog.list.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(EditDialog.second.getText()));
                    Path.editAction(EditDialog.actionId, newAction);
                    EditDialog.close();
                }
            }
        });
    }

    public static void close(){
        Stage stage = (Stage) EditDialog.close.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EditDialog.handleClose();
        EditDialog.handleEdit();
    }
}
