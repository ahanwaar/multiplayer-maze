package com.worldnavigator.multiplayermaze.game.commands.trade;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.exceptions.NoSuchItemException;
import com.worldnavigator.multiplayermaze.game.maze.items.Item;
import com.worldnavigator.multiplayermaze.game.maze.items.ItemFactory;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.Seller;
import com.worldnavigator.multiplayermaze.game.player.Player;
import org.springframework.stereotype.Component;

@Component
public class BuyCommand implements Command {

  @Override
  public String execute(Player player, String... args) {

    String itemName = String.join(" ", args);

    Seller seller = (Seller) player.getCurrentWall();
    int itemPrice = seller.getItemPrice(itemName);

    if (player.getInventory().containsItem(itemName)) {
      return "You don't need this item," + " It's already in your inventory!";
    }

    if (player.getInventory().getGold().getAmount() >= itemPrice) {
      player.getInventory().getGold().withdrawGoldAmount(itemPrice);
      try {
        return addPurchasedItem(player, itemName);
      } catch (NoSuchItemException e) {
        e.printStackTrace();
      }
    }
    return String.format("Your gold amount isn't enough to buy <%s> !", itemName);
  }

  private String addPurchasedItem(Player player, String itemName) throws NoSuchItemException {
    Item item = ItemFactory.getItem(itemName);
    player.getInventory().addItem(item);
    return String.format("The %s has been added to your inventory", itemName);
  }

  @Override
  public String getDescription() {
    return "Use this command to buy an item from the facing seller!";
  }

  @Override
  public String name() {
    return "buy";
  }

  @Override
  public boolean checkAvailability(Player player) {
    return false;
  }

  @Override
  public String args() {
    return "<item>";
  }

  public boolean validate(Player player, String... args) {
    return args.length >= 1;
  }

}
