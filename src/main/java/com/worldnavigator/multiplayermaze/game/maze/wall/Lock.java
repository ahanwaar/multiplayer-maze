package com.worldnavigator.multiplayermaze.game.maze.wall;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.worldnavigator.multiplayermaze.game.exceptions.NullLockException;
import com.worldnavigator.multiplayermaze.game.maze.deseializers.ItemDeserializer;
import com.worldnavigator.multiplayermaze.game.maze.items.Key;


import java.util.Objects;

public class Lock {

  @JsonDeserialize(using = ItemDeserializer.class)
  private final Key key;
  private boolean locked;

  @JsonCreator
  public Lock(
      Key key
  ) {
    this.key = Objects.requireNonNull(key);
    locked = !key.equals(Key.NULL);
  }

  public boolean unLock(Key key) {
    if (key.equals(Key.NULL)) {
      throw new NullLockException();
    } else if (this.key.equals(key)) {
      locked = !locked;
      return true;
    } else {
      return false;
    }
  }

  public boolean isLocked() {
    return this.locked;
  }

  public Key getKey() {
    return this.key;
  }

  @Override
  public String toString() {
    return "Lock{" +
        "key=" + key +
        ", locked=" + locked +
        '}';
  }
}
