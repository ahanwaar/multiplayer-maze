package com.worldnavigator.multiplayermaze.game.maze.items;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Item {

  @JsonIgnore
  String getName();

  String accept(ItemVisitor itemVisitor);

}
