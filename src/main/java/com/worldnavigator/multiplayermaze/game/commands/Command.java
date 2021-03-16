package com.worldnavigator.multiplayermaze.game.commands;


import com.worldnavigator.multiplayermaze.game.player.Player;

public interface Command {

  String execute(Player player, String... args);

  String getDescription();

  String name();

  boolean checkAvailability(Player player);

  default String args() {
    return "";
  }

  default boolean validate(Player player, String... args) {
    return true;
  }
}
