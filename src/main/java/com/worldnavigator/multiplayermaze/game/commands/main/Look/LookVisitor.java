package com.worldnavigator.multiplayermaze.game.commands.main.Look;


import com.worldnavigator.multiplayermaze.game.maze.wall.WallVisitor;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.*;

public class LookVisitor implements WallVisitor {

  @Override
  public String visitPlainWall(PlainWall plainWall) {
    return "You are facing a wall!";
  }

  @Override
  public String visitDoor(Door door) {
    return "You are facing a door!";
  }

  @Override
  public String visitChest(Chest chest) {
    return "You are facing a chest!";
  }

  @Override
  public String visitPainting(Painting painting) {
    return "You are facing a painting!";
  }

  @Override
  public String visitMirror(Mirror mirror) {
    return "You see a silhouette of you!";
  }

  @Override
  public String visitSeller(Seller seller) {
    return "You are facing a seller!";
  }
}
