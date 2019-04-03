package ug.mak.distributed.taskbag;

import ug.mak.distributed.Pair;

public interface Worker {
    Pair pickTask();
    boolean updateWithResult(Pair pair);
    boolean addTask(Pair pair);
}
