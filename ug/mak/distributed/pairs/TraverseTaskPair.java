package ug.mak.distributed.pairs;

import ug.mak.distributed.tasks.TraverseTask;

import java.io.Serializable;

public class TraverseTaskPair extends Pair implements Serializable {
    private TraverseTask traverseTask;

    public TraverseTaskPair(String taskName, TraverseTask traverseTask) {
        super(taskName);
        this.traverseTask = traverseTask;
    }

    public TraverseTask getTraverseTask() {
        return traverseTask;
    }
}
