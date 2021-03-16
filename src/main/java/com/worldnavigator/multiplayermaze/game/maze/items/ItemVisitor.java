package com.worldnavigator.multiplayermaze.game.maze.items;

public interface ItemVisitor {

    String visitFlashlight(Flashlight flashlight);

    String visitKey(Key key);

    String visitGold(Gold gold);
}
