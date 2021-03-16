package com.worldnavigator.multiplayermaze.game.maze.wall;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.*;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlainWall.class, name = "plainWall"),
        @JsonSubTypes.Type(value = Door.class, name = "door"),
        @JsonSubTypes.Type(value = Mirror.class, name = "mirror"),
        @JsonSubTypes.Type(value = Painting.class, name = "painting"),
        @JsonSubTypes.Type(value = Seller.class, name = "seller"),
        @JsonSubTypes.Type(value = Chest.class, name = "chest")
})
public abstract class Wall {

  public abstract String accept(WallVisitor visitor);

  @Override
  public String toString() {
    return super.toString();
  }

  public abstract String getName();
}
