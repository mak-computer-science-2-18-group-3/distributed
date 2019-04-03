package ug.mak.distributed.taskbag;

import ug.mak.distributed.Pair;
import ug.mak.distributed.maze.Maze;

public interface Master {
    boolean setupMaze(Maze maze);
    boolean addTask(Pair pair);
}
