package com.worldnavigator.multiplayermaze.game.maze.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Flashlight implements Item {

  @JsonIgnore
  private boolean isOn;

  @JsonCreator
  public Flashlight() {
    this.isOn = false;
  }

  public void turnOn() {
    this.isOn = true;
  }

  public boolean isOn() {
    return isOn;
  }

  @Override
  public String getName() {
    return "flashlight";
  }

  @Override
  public String accept(ItemVisitor itemVisitor) {
    return itemVisitor.visitFlashlight(this);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Flashlight that = (Flashlight) o;
    return getName().equals(((Flashlight) o).getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public String toString() {
    return "flashlight";
  }
}
