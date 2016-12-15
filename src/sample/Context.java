package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by dev on 17/11/16.
 */
public class Context {

    public Action editedAction = null;
    public boolean newAction = false;
    public ArrayList<ArrayList<Action>> allActions = new ArrayList<ArrayList<Action>>();
    public int editedActionIndex;
    public ObservableList<Action> actionList = FXCollections.observableArrayList();

    public void setEditedAction(Action a){
        editedAction = a;
    }

    public void setActionList(ObservableList<Action> actionList) {
        this.actionList = actionList;
    }

    public void setEditedActionIndex(int a){
        editedActionIndex = a;
    }

    public Action getEditedAction(){
        return editedAction;
    }

    public int getEditedActionIndex(){
        return editedActionIndex;
    }

    public ObservableList<Action> getActionList() {
        return actionList;
    }
}
