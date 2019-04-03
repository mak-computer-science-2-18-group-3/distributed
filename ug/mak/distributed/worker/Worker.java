public class Worker{
  Maze maze;
  // compute the task
  public Pair computeTask(Pair pair){
    if(!(workerStub.tasks.contains(pair))){
        // If no matching pair
        this.wait();
    }else if((workerStub.tasks.contains(pair)).length > 1)){
        // If several matching pairs, pick one arbitrarily
        int index = (int) (Math.random() * workerStub.tasks.length);
        pair = (Pair) workerStub.tasks[index];
    }else{
        pair = pair;
    }
    // toDo: result = code to traverse maze
    return result;
  }

  public void wait(){
    // wait for 2 seconds
    // current worker thread: this.sleep(2000);
  }

  public Pair traverse(Pair pair){
      // Explore the maze according to task description of pair 
  }
}
