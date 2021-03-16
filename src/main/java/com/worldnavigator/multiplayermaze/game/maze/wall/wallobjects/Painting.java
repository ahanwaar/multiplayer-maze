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

public class Painting extends Wall implements HiddenKey {

    @JsonDeserialize(using = ItemDeserializer.class)
    private Key hiddenKey;

  @JsonCreator
  public Painting(Key key) {//you said the can be empty! no!yes but empty means Key.null :3
    this.hiddenKey = Objects.requireNonNull(key);
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitPainting(this);
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
        key = hiddenKey;
        this.hiddenKey = Key.NULL;
      } else {
        throw new NullItemException("There is no hidden key");
      }
    } catch (NullItemException e) {
        e.printStackTrace();
    }
      return key;
  }

    @Override
    public String toString() {
        return "Mirror{" + "hiddenKey=" + hiddenKey + '}';
    }

    @Override
    public String getName() {
        return "painting";
    }
}
