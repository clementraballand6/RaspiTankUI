package sample;

/**
 * Created by craballand.cpi2019 on 28/10/2016.
 */
public class Action {
    public String action;
    public int duration;

    public Action(String action, int duration) {
        this.action = action;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return this.action + " during " + this.duration + " second(s)";
    }
}
