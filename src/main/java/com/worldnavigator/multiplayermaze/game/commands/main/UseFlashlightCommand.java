package com.worldnavigator.multiplayermaze.game.commands.main;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.maze.Room;
import com.worldnavigator.multiplayermaze.game.maze.items.Flashlight;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class UseFlashlightCommand implements Command {

  @Override
  public String name() {
    return "useFlashlight";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }

  @Override
  public String execute(Player player, String... args) {
    Room room = player.getCurrentRoom();
    if (player.getInventory().containsItem("flashlight")) {
        Flashlight flashlight = (Flashlight) player.getInventory().getItems().get("flashlight");

        if (room.isLit()) {
            return "The room is already lit! save your batteries for harder times!";
        } else {
            room.switchLights();
            flashlight.turnOn();
            return "The room is lit rn!";
        }
    } else {
      return "You don't have a flashlight!";
    }
  }

  @Override
  public String getDescription() {
    return "use this command to use the flashlight if you have it.";
  }
}
