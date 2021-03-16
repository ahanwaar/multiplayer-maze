package com.worldnavigator.multiplayermaze.game.commands.fight;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.conflict.ConflictHandler;
import com.worldnavigator.multiplayermaze.game.conflict.Hand;
import com.worldnavigator.multiplayermaze.game.conflict.RockPaperScissorsFight;
import com.worldnavigator.multiplayermaze.game.major.Game;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class PlayCommand implements Command {

  private final ConflictHandler handler;

  @Autowired
  public PlayCommand(ConflictHandler handler) {
    this.handler = handler;
  }

  @Override
  public String execute(Player player, String... args) {

    Hand hand = getHand(args[0]);

    Game game = player.getGame();

    RockPaperScissorsFight fight = game.getFightByPlayer(player);

    if (fight.start(player, hand)) {
      return handler.status(player, fight);
    }

    return "You already played your turn, you can't change your hand now!";
  }

  private Hand getHand(String name) {
    if ("rock".startsWith(name)) {
      return Hand.ROCK;
    } else if ("paper".startsWith(name)) {
      return Hand.PAPER;
    } else if ("scissor".startsWith(name)) {
      return Hand.SCISSOR;
    }

    return Hand.NULL;
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getPlayerStatus() == PlayerStatus.FIGHTING;
  }

  @Override
  public String name() {
    return "fight:play";
  }

  @Override
  public String args() {
    return "<rock|paper|scissor>";
  }

  @Override
  public String getDescription() {
    return "Plays a hand in a rock paper scissor fight.";
  }

  @Override
  public boolean validate(Player player, String... args) {
    return args.length == 1
        && ("rock".startsWith(args[0])
        || "paper".startsWith(args[0])
        || "scissor".startsWith(args[0]));
  }
}
