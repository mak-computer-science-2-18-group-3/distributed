package ug.mak.distributed.worker;

import tasks.TraverseTask;

public class Worker{
  // compute the task
  public Pair computeTask(Pair pair){
      if(!(workStub.tasks.contains(pair))){
          // If no matching pair
          this.wait();
      }else if((workStub.tasks.contains(pair)).length > 1)){
          // If several matching pairs, pick one arbitrarily
          int index = (int) (Math.random() * workStub.tasks.length);
          pair = (Pair) workStub.tasks[index];
        }else{
          pair = pair;
      }
      TraverseTask traverseTask = (TraverseTask) pair.getObject();

      
  }

  public void wait(){
    // wait for 2 seconds
    try{
      Thread.currentThread().sleep(2000);
    }catch(Exception ex){ex.printStackTrace();}

  }
}
