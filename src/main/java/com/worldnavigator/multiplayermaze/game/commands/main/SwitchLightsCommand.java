package com.worldnavigator.multiplayermaze.game.commands.main;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.maze.Room;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class SwitchLightsCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Room room = player.getCurrentRoom();
    if (room.hasLights()) {
      room.switchLights();
      if (room.isLit()) {
        return "The room is lit now!";
      } else {
        return "The room is dark now!";
      }
    }
    return "The room doesn't have lights!";
  }

  @Override
  public String getDescription() {
    return "use to switch the room lights";
  }

  @Override
  public String name() {
    return "switch-lights";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }
}
