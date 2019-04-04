package ug.mak.distributed.pairs;

import java.io.Serializable;

public abstract class Pair implements Serializable {
    private String taskName;

    public Pair(String taskName){
        this.taskName = taskName;
    }
}
