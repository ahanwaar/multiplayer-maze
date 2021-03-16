package com.worldnavigator.multiplayermaze.game.maze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.worldnavigator.multiplayermaze.game.maze.deseializers.ItemDeserializer;
import com.worldnavigator.multiplayermaze.game.maze.items.Gold;
import com.worldnavigator.multiplayermaze.game.maze.items.Item;
import com.worldnavigator.multiplayermaze.game.maze.items.Key;
import com.worldnavigator.multiplayermaze.game.maze.items.PriceList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Inventory {

  @JsonIgnore
  private final PriceList priceList = PriceList.getInstance();
  @JsonDeserialize(using = ItemDeserializer.class)
  private final Map<String, Item> items;
  @JsonProperty("gold")
  private Gold gold;

  @JsonCreator
  public Inventory() {
    this.gold = new Gold(0);
    this.items = new HashMap<>();
  }

  public Map<String, Item> getItems() {
    return items;
  }

  public void addItems(Map<String, Item> items) {
    for (Item item : items.values()) {
      if (containsItem(item.getName())) {
        gold.addGoldAmount(priceList.getItemPrice(item.getName()));
      } else {
        this.items.putIfAbsent(item.getName(), item);
      }
    }
  }

  public void removeItem(Item item) {
    Objects.requireNonNull(item);
    if (items.containsKey(item.getName()))
      items.remove(item);
  }

  public void mergeInventories(Inventory inventory) {
    addItems(inventory.getItems());
    this.gold.addGoldAmount(inventory.getGold().getAmount());
  }

  public Gold getGold() {
    return gold;
  }

  public boolean containsItem(String itemName) {
    return items.containsKey(itemName);
  }

  public void addItem(Item item) {
    Objects.requireNonNull(item);
    if (item instanceof Key && item.equals(Key.NULL)) {
      return;
    }
    if (containsItem(item.getName())) {
      gold.addGoldAmount(priceList.getItemPrice(item.getName()));
    } else {
      this.items.putIfAbsent(item.getName(), item);
    }
  }

  public Item getItem(String itemName) {
    Objects.requireNonNull(itemName);
    if (!containsItem(itemName)) {
      throw new IllegalArgumentException("The Item isn't existing");
    }
    return items.get(itemName);
  }

  public int convertToGoldAmount() {
    int total = gold.getAmount();
    for (Item item : items.values()) {
      total += priceList.getItemPrice(item.getName());
    }
    return total;
  }

  @Override
  public String toString() {
    return "inventory";
  }
}
