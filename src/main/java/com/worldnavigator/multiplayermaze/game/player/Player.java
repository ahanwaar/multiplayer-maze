package com.worldnavigator.multiplayermaze.game.player;


import com.worldnavigator.multiplayermaze.game.major.Game;
import com.worldnavigator.multiplayermaze.game.maze.Direction;
import com.worldnavigator.multiplayermaze.game.maze.Inventory;
import com.worldnavigator.multiplayermaze.game.maze.Room;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Player {

    @EqualsAndHashCode.Include
    private final String userName;
    private Inventory inventory = new Inventory();
    private PlayerStatus playerStatus;
  private Game game;
  private int roomIndex;
  private Direction direction;

    public Player(
            String userName, int goldAmount, int roomIndex, Direction direction) {

        this.userName = userName;
        this.inventory.getGold().addGoldAmount(goldAmount);
        this.roomIndex = roomIndex;
        this.direction = direction;
    }

    public Player(String userName) {
        Objects.requireNonNull(userName);
        this.userName = userName;
        this.playerStatus = PlayerStatus.WAITING;
    }

    public Room getCurrentRoom() {
        return this.game.getGameConfig().getMaze().getRoom(roomIndex);
    }

    public Wall getCurrentWall() {
        return getCurrentRoom().getWall(direction);
    }

    public int getTotalGoldValue() {
    return getInventory().convertToGoldAmount();
  }
}
