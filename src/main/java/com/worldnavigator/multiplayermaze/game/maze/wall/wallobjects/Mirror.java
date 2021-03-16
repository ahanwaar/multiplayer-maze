package com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.worldnavigator.multiplayermaze.game.exceptions.NullItemException;
import com.worldnavigator.multiplayermaze.game.maze.deseializers.ItemDeserializer;
import com.worldnavigator.multiplayermaze.game.maze.items.Key;
import com.worldnavigator.multiplayermaze.game.maze.wall.HiddenKey;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.WallVisitor;

import java.util.Objects;

public class Mirror extends Wall implements HiddenKey {

  @JsonDeserialize(using = ItemDeserializer.class)
  private Key hiddenKey;

  @JsonCreator
  public Mirror(Key key) {
    this.hiddenKey = Objects.requireNonNull(key);
  }

  public void acquireKey(Key hiddenKey) {
    this.hiddenKey = hiddenKey;
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitMirror(this);
  }

  @Override
  public boolean hasHiddenKey() {
    return !hiddenKey.equals(Key.NULL);
  }

  @Override
  public Key grabKey() {
    Key key = Key.NULL;
    try {
      if (hasHiddenKey()) {
        this.hiddenKey = Key.NULL;
        return hiddenKey;
      } else {
        throw new NullItemException("there is no hidden key");
      }
    } catch (NullItemException e) {
      e.printStackTrace();
    }
    return key;
  }

  @Override
  public String toString() {
    return "Mirror{" + "hidden key=" + hiddenKey + '}';
  }

  @Override
  public String getName() {
    return "mirror";
  }
}
