package ug.mak.distributed;

public class Task {
    boolean taken;

    public Task(){
        taken = false;
    }

    public void take() {
        this.taken = true;
    }

    public boolean isNotTaken() {
        return !taken;
    }
}
