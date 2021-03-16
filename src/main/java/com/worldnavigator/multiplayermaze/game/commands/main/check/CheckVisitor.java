package com.worldnavigator.multiplayermaze.game.commands.main.check;


import com.worldnavigator.multiplayermaze.game.maze.Inventory;
import com.worldnavigator.multiplayermaze.game.maze.wall.WallVisitor;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.*;
import com.worldnavigator.multiplayermaze.game.player.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckVisitor implements WallVisitor {

  private final Player player;

  @Override
  public String visitPlainWall(PlainWall plainWall) {
    return "It's a plain wall, nothing here!";
  }

  @Override
  public String visitDoor(Door door) {
    if (door.getLock().isLocked()) {
      return "Door is locked, <"
          + door.getLock().getKey().getName()
          + "> is needed to unlock!";
    } else {
      if (door.isOpen()) {
        return "The door is open, you can move forward";
      } else {
        return "The door is not open!";
      }
    }
  }

  @Override
  public String visitChest(Chest chest) {
    if (chest.getLock().isLocked()) {
      return "Chest is locked, <"
          + chest.getLock().getKey().getName()
          + "> is needed to unlock!";
    } else {
      if (chest.isLooted()) {
        return "Someone else looted this chest before you!";
      } else {
        Inventory inventory = chest.getInventory();
        player.getInventory().mergeInventories(inventory);
        chest.setLooted(true);
        return "You have acquired the chest Inventory.";
      }
    }
  }

  @Override
  public String visitPainting(Painting painting) {
    if (painting.hasHiddenKey()) {
      String str = "The " + painting.grabKey().toString() + " has looted!";
      player.getInventory().addItem(painting.grabKey());
      return str;
    } else {
      return "There is nothing behind the painting";
    }
  }

  @Override
  public String visitMirror(Mirror mirror) {
    if (mirror.hasHiddenKey()) {
      String str = "The " + mirror.grabKey().toString() + " has looted!";
      player.getInventory().addItem(mirror.grabKey());
      return str;
    } else {
      return "There is nothing behind the painting";
    }
  }

  @Override
  public String visitSeller(Seller seller) {
    return "There is a seller";
  }
}
