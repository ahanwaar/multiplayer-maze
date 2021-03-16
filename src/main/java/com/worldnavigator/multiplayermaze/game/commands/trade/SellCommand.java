package com.worldnavigator.multiplayermaze.game.commands.trade;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.exceptions.NoSuchItemException;
import com.worldnavigator.multiplayermaze.game.maze.items.Item;
import com.worldnavigator.multiplayermaze.game.maze.items.ItemFactory;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.Seller;
import com.worldnavigator.multiplayermaze.game.player.Player;
import org.springframework.stereotype.Component;

@Component
public class SellCommand implements Command {

  @Override
  public String execute(Player player, String... args) {
    String itemName = String.join(" ", args);
    Seller seller = (Seller) player.getCurrentWall();
    int itemPrice = seller.getItemPrice(itemName);

    if (!player.getInventory().containsItem(itemName)) {
      return "You can't sell something that you do not own!";
    }

    Item item = null;
    try {
      item = ItemFactory.getItem(itemName);
    } catch (NoSuchItemException e) {
      e.printStackTrace();
    }

    player.getInventory().getGold().addGoldAmount(itemPrice);
    player.getInventory().removeItem(item);

    return String.format("You sold the %s for " + itemPrice + " gold coins", item);
  }

  @Override
  public String getDescription() {
    return "use this command to sell an item to the seller!";
  }

  @Override
  public String name() {
    return "trade:sell";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return player.getCurrentWall() instanceof Seller;
  }

  @Override
  public boolean validate(Player player, String... args) {
    return args.length >= 1;
  }

  @Override
  public String args() {
    return "<item>";
  }
}
