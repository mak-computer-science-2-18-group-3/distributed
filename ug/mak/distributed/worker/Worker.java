package ug.mak.distributed.worker;

import tasks.TraverseTask;
import ug.mak.distributed.taskbag;

public class Worker{
  public Worker(TaskBag object){}

  private void endCompute(List<Cell> visitedCells){
    // todo create pair with traverse task
    Pair pairOut = (Pair) this.addTask(visitedCells);

  }

  private void endComputeWithNextTasks(List<Cell> nextTaskCells, List<Cell> visitedCells){
    endCompute(visitedCells);
    // todo create pairs with next tasks and call addTask on TaskBag
    Pair pairOut = this.addTask(nextTaskCells, visitedCells);
  }

  // compute the task
  public void computeTask(Pair pair){
      Task task = (Task) pair.getObject();
      Maze maze = task.getMaze();
      Cell cell = task.start;

      Cell topCell, leftCell, bottomCell, rightCell;

      topCell = maze.getCell(cell.row - 1, cell.col);
      rightCell = maze.getCell(cell.row, cell.col + 1);
      bottomCell = maze.getCell(cell.row + 1, cell.col);
      leftCell = maze.getCell(cell.row, cell.col - 1);

      List<Cell> visitedCells = new ArrayList();

      while(true){
        if (cell == null){
          break;
        }

        String direction;
        int possibleDirections = 0;

        // for scenario where we have more than one possible direction
        List<Cell> nextTaskCells = new ArrayList();

        if(cell.isTopOpen() && topCell && !topCell.isVisited()){
            possibleDirections++;
            nextTaskCells.add(topCell);
            direction = "TOP";
        }else if(cell.isRightOpen() && rightCell && !rightCell.isVisited()){
            possibleDirections++;
            nextTaskCells.add(rightCell);
            direction = "RIGHT";
        }else if(cell.isBottomOpen() && bottomCell && !bottomCell.isVisited()){
            possibleDirections++;
            nextTaskCells.add(bottomCell);
            direction = "BOTTOM";
        }else if(cell.isLeftOpen() && leftCell && !leftCell.isVisited()){
            possibleDirections++;
            nextTaskCells.add(leftCell);
            direction = "LEFT";
        }

        cell.visit();
        visitedCells.add(cell);

        if (possibleDirections > 1){
          // todo call add task with pair tasks as nextTaskCells
          endComputeWithNextTasks(nextTaskCells, visitedCells);
          return;
        }
        else if (possibleDirections < 1) {
          // dead end, probably end
          endCompute(visitedCells);
          return;
        }
        else{
          int row = cell.row;
          int col = cell.col;
          switch (direction) {
            case "UP": {
              row = cell.row - 1;
              break;
            }
            case "DOWN":{
              row = cell.row + 1;
              break;
            }
            case "LEFT": {
              col = cell.col - 1;
              break;
            }
            case "RIGHT":{
              col = cell.col + 1;
              break;
            }
          }
          cell = maze.getCell(row, col);
        }
      }

      endCompute(visitedCells);

      // for(int i = 0; i <= task.getEnd()[0] - start[0]; i++){
      //   for(int j = 0; j < task.getEnd()[1] - start[1]; j++ ){
      //       Cell cell = (Cell) maze.getCell(i,j);
      //       if(cell.isTopOpen() & !cell.isVisited()){
      //           task = cell.visit();
      //       }else if(cell.isRightOpen() & !cell.isVisited()){
      //           task = cell.visit();
      //       }else if(cell.isBottomOpen() & !cell.isVisited()){
      //           task = cell.visit();
      //       }else if(cell.isLeftOpen() & !cell.isVisited()){
      //           task = cell.visit();
      //       }else{
      //           return task = cell.isVisited();
      //       }
      //   }
      //
      // }

      // Pair nextPair = (Pair) task.getObject();

  }

  // public int[] checkForNextCell () {
  //   int[] end = [];
  //   Maze.getCell(start){
  //     if topIsOpen && !visited,
  //   }
  // }



  public void wait(){
    // wait for 2 seconds
    try{
      Thread.currentThread().sleep(2000);
    }catch(Exception ex){ex.printStackTrace();}
  }
}
