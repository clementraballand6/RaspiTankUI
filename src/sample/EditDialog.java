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

    public Action action;
    public ChoiceBox list;
    public TextField second;
    public Button close;
    public Button edit;

    public void handleClose() {
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    close();
                }
            }
        });
    }

    public void handleEdit() {
        edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    Action newAction = new Action(list.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(second.getText()), action.index);
                    Main.context.setEditedAction(newAction);
                    close();
                }
            }
        });
    }

    public void close(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        action = Main.context.getEditedAction();

        list.setItems(FXCollections.observableArrayList(
                "Forward", "Backward", "Left", "Right"
        ));
        second.setText(Integer.toString(action.duration));
        list.getSelectionModel().select(action.action);
        handleClose();
        handleEdit();
    }
}
