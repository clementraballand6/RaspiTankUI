package sample;

/**
 * Created by craballand.cpi2019 on 28/10/2016.
 */
public class Action {
    public int index;
    public String action;
    public int duration;

    public Action(String action, int duration, int index) {
        this.index = index;
        this.action = action;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return this.index + " " + this.action + " during " + this.duration + " second(s)";
    }
}
