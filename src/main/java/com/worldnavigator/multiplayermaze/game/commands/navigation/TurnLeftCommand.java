package com.worldnavigator.multiplayermaze.game.commands.navigation;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TurnLeftCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Objects.requireNonNull(player);
    player.setDirection(player.getDirection().turnLeft());
    return "You turned left!";
  }

  @Override
  public String getDescription() {
    return "Use this command to turn left!";
  }

  @Override
  public String name() {
    return "turnLeft";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }
}
