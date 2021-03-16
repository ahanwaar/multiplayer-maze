package com.worldnavigator.multiplayermaze.game.commands.main;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.Door;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class OpenDoorCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Door door = (Door) player.getCurrentRoom().getWall(player.getDirection());

    if (door.getLock().isLocked()) {
      return String.format(
          "The door is locked, you need a %s to unlock it!", door.getLock().getKey());
    } else {
      if (door.isOpen()) {
        return "The door is already open!";
      } else {
        door.open();
        return "The door is now open!";
      }
    }
  }

  @Override
  public String getDescription() {
    return "use this command to open unlocked doors";
  }

  @Override
  public String name() {
    return "open";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getCurrentWall() instanceof Door
        && player.getPlayerStatus() == PlayerStatus.WALKING;
  }
}
