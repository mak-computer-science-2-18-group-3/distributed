package tasks;

public class TraverseTask {
    private int[] start;
    private int[] end;

    public TraverseTask(int[] start, int[] end){
        this.start = start;
        this.end = end;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }
}
