package ug.mak.distributed.worker;

import tasks.TraverseTask;
import Work.workStub;
import ug.mak.distributed.taskbag;

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
      Pair pairOut = updateWithResult();
  }

  public void wait(){
    // wait for 2 seconds
    try{
      Thread.currentThread().sleep(2000);
    }catch(Exception ex){ex.printStackTrace();}

  }

  public public boolean updateWithResult(Pair pair) {
      TraverseTask traverseTask = (TraverseTask) pair.getObject();
      for (int i = 0; i <= traverseTask.getEnd()[0] - traverseTask.getStart()[0]; i++) {
          for (int j = 0; j < traverseTask.getEnd()[1] - traverseTask.getStart()[1]; j++) {
              maze.getCell(i, j).visit();
          }
      }
      return true;
  }
}
