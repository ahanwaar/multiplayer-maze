package com.worldnavigator.multiplayermaze.game.major;

import com.worldnavigator.multiplayermaze.game.maze.Maze;
import com.worldnavigator.multiplayermaze.game.mazegenerator.MazeGenerator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameConfig {

  private int initialGoldAmount;
  private int timeOut;
  private int playersNumber;
  private final Maze maze;

  public GameConfig(int initialGoldAmount, int timeOut, int playersNumber) {
    this.initialGoldAmount = initialGoldAmount;
    this.timeOut = timeOut;
    this.playersNumber = playersNumber;
    this.maze = new MazeGenerator().generateMaze(playersNumber);
  }


}
