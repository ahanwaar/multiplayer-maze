package com.worldnavigator.multiplayermaze.game.maze.wall;


import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.*;

public interface WallVisitor {

  String visitPlainWall(PlainWall plainWall);

  String visitDoor(Door door);

  String visitChest(Chest chest);

  String visitPainting(Painting painting);

  String visitMirror(Mirror mirror);

  String visitSeller(Seller seller);

}
