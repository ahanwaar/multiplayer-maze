package com.worldnavigator.multiplayermaze.game.maze.items;

import com.worldnavigator.multiplayermaze.game.exceptions.NoSuchItemException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class PriceList {

  private static final PriceList priceList = new PriceList();

  private Map<String, Integer> prices;

  private PriceList() {
    this.prices = new HashMap<>();
    this.prices.putIfAbsent("flashlight", 20);
    this.prices.putIfAbsent("copperKey", 20);
    this.prices.putIfAbsent("silverKey", 30);
    this.prices.putIfAbsent("goldKey", 40);
  }

  public static PriceList getInstance() {
    return priceList;
  }


  public int getItemPrice(String itemName) {
    Objects.requireNonNull(itemName);
    int price = 0;
    try {
      if (prices.containsKey(itemName)) {
        price = prices.get(itemName);
      } else {
        throw new NoSuchItemException(String.format("There no such item in the price list with %s name", itemName));
      }
    } catch (NoSuchItemException e) {
      e.printStackTrace();
    }
    return price;
  }

  @Override
  public String toString() {
    return "PriceList{" +
        "prices=" + prices +
        '}';
  }
}
