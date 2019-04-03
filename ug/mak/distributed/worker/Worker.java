public class Worker{
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
      return result = this.traverseTask(pair);
  }

  public void wait(){
    // wait for 2 seconds
    try{
      Thread.currentThread().sleep(2000);
    }catch(Exception ex){ex.printStackTrace();}

  }

  public Pair traverseTask(Pair pair){
      // Explore the maze according to task description of pair
  }
}
