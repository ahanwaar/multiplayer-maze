package com.worldnavigator.multiplayermaze.game.maze.wall;


import com.worldnavigator.multiplayermaze.game.exceptions.NullItemException;
import com.worldnavigator.multiplayermaze.game.maze.items.Key;

public interface HiddenKey {

  boolean hasHiddenKey();

  Key grabKey() throws NullItemException;

}
