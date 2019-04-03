package ug.mak.distributed.taskbag;

import ug.mak.distributed.Pair;
import ug.mak.distributed.master.Callback;
import ug.mak.distributed.maze.Maze;

public interface Master {
    boolean setupMaze(Maze maze, Callback callback);
    boolean addTask(Pair pair);
}
