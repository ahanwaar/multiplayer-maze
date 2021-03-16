package com.worldnavigator.multiplayermaze.game.commands.navigation;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TurnRightCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Objects.requireNonNull(player);
    player.setDirection(player.getDirection().turnRight());
    return "You turned right!";
  }

  @Override
  public String getDescription() {
    return "Use this command to turn right!";
  }

  @Override
  public String name() {
    return "turnRight";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING;
  }
}
