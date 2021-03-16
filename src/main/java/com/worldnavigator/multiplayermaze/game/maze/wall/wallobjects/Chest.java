package com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.worldnavigator.multiplayermaze.game.maze.Inventory;
import com.worldnavigator.multiplayermaze.game.maze.wall.Lock;
import com.worldnavigator.multiplayermaze.game.maze.wall.Lockable;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.WallVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Chest extends Wall implements Lockable {

  private final Inventory inventory;
  private Lock lock;
  private boolean looted;

  @JsonCreator
  public Chest(Lock lock, Inventory inventory) {
    this.lock = Objects.requireNonNull(lock);
    this.inventory = Objects.requireNonNull(inventory);
    this.looted = false;
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitChest(this);
  }

  @Override
  public String toString() {
    return "Chest{" + "inventory=" + inventory + ", lock=" + lock + ", looted=" + looted + '}';
  }

  @Override
  public String getName() {
    return "chest";
  }
}
