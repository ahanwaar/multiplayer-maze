package com.worldnavigator.multiplayermaze.game.commands.main;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.major.Game;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class QuitCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Objects.requireNonNull(player);
    Game game = player.getGame();
    player.setPlayerStatus(PlayerStatus.LOST);
    game.distributeGold(player.getInventory().convertToGoldAmount());
    player.getCurrentRoom().removePlayer(player);
    game.getPlayers().remove(player);
    return "You quit the game";
  }

  @Override
  public String getDescription() {
    return "use this command to quit the game";
  }

  @Override
  public String name() {
    return "quit";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING
        || player.getPlayerStatus() == PlayerStatus.FIGHTING;
  }
}
