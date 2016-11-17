package sample;

/**
 * Created by dev on 17/11/16.
 */
public class Context {

    public Action editedAction = null;
    public int editedActionIndex;

    public void setEditedAction(Action a){
        editedAction = a;
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
}
