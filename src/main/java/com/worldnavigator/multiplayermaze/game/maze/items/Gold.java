package com.worldnavigator.multiplayermaze.game.maze.items;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Gold implements Item {

  int amount;

  @JsonCreator
  public Gold(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Gold amount can't be negative");
    }
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void addGoldAmount(int amount) {
    this.amount += amount;
  }

  public void withdrawGoldAmount(int amount) {
    this.amount -= amount;
  }

  public String getName() {
    return "gold";
  }

  @Override
  public String accept(ItemVisitor itemVisitor) {
    return itemVisitor.visitGold(this);
  }

}
