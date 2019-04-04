package ug.mak.distributed.maze;

import java.io.Serializable;

public class Cell implements Serializable {
    private boolean topOpen;
    private boolean rightOpen;
    private boolean bottomOpen;
    private boolean leftOpen;
    private boolean visited;
    public int row;
    public int col;

    public Cell(boolean topOpen, boolean rightOpen, boolean bottomOpen, boolean leftOpen, int row, int col){
        this.topOpen = topOpen;
        this.rightOpen = rightOpen;
        this.bottomOpen = bottomOpen;
        this.leftOpen = leftOpen;
        visited = false;
        this.row = row;
        this.col = col;
    }

    public Cell(int topOpen, int rightOpen, int bottomOpen, int leftOpen, int row, int col){
        this.topOpen = topOpen == 0;
        this.rightOpen = rightOpen == 0;
        this.bottomOpen = bottomOpen == 0;
        this.leftOpen = leftOpen == 0;
        visited = false;
        this.row = row;
        this.col = col;
    }

    public void visit(){
        this.visited = true;
    }

    public boolean isTopOpen() {
        return topOpen;
    }

    public boolean isRightOpen() {
        return rightOpen;
    }

    public boolean isBottomOpen() {
        return bottomOpen;
    }

    public boolean isLeftOpen() {
        return leftOpen;
    }

    public boolean isVisited() {
        return visited;
    }
}
