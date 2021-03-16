package com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.worldnavigator.multiplayermaze.game.maze.items.PriceList;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.WallVisitor;


public class Seller extends Wall {

  private final PriceList pricesList;

  @JsonCreator
  public Seller(PriceList pricesList) {
    this.pricesList = pricesList;
  }

  public PriceList getPricesList() {
    return pricesList;
  }

  public Integer getItemPrice(String itemName) {
    return pricesList.getItemPrice(itemName);
  }

  @Override
  public String accept(WallVisitor visitor) {
      return visitor.visitSeller(this);
  }

    @Override
    public String toString() {
        return "Seller{" +
                "pricesList=" + pricesList +
                '}';
    }

    @Override
    public String getName() {
        return "seller";
    }
}
