package ug.mak.distributed;

public class Pair {
    private String taskName;
    private Object object;

    public Pair(String taskName, Object object){
        this.taskName = taskName;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
