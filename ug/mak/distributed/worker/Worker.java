package ug.mak.distributed.worker;

import tasks.TraverseTask;
import ug.mak.distributed.taskbag;

public class Worker{
  public Worker(TaskBag object){}

  // compute the task
  public Pair computeTask(Pair pair){
      // if(!(worker.tasks.contains(pair))){
      //     // If no matching pair
      //     this.wait();
      // }else if((worker.tasks.contains(pair)).length > 1)){
      //     // If several matching pairs, pick one arbitrarily
      //     int index = (int) (Math.random() * worker.tasks.length);
      //     pair = (Pair) worker.tasks[index];
      //   }else{
      //     pair = pair;
      // }
      updateWithResult(pair);
  }

  public void wait(){
    // wait for 2 seconds
    try{
      Thread.currentThread().sleep(2000);
    }catch(Exception ex){ex.printStackTrace();}
  }
}
