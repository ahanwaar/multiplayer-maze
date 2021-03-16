package com.worldnavigator.multiplayermaze.game.player;

import com.worldnavigator.multiplayermaze.game.maze.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PlayerLocation {

  private int roomIndex;
  private Direction direction;
}
