package com.worldnavigator.multiplayermaze.game.commands.main.check;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class CheckCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    Wall wall = player.getCurrentWall();
    return wall.accept(new CheckVisitor(player));
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.WALKING && player.getCurrentRoom().isLit();
  }

  @Override
  public String name() {
    return "check";
  }

  @Override
  public String getDescription() {
    return "Use this command to check the in front wall object.";
  }
}
