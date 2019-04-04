package ug.mak.distributed.pairs;

import ug.mak.distributed.tasks.Task;

import java.io.Serializable;

public class TaskPair extends Pair implements Serializable {
    private Task task;

    public TaskPair(String taskName, Task task) {
        super(taskName);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
