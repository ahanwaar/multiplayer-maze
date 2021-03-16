package com.worldnavigator.multiplayermaze.game.maze;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Maze {

  private final List<Room> rooms;

  @JsonCreator
  public Maze(List<Room> rooms) {
    this.rooms = rooms;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public Room getRoom(int index) {
    return rooms.get(index);
  }

  public int getNumOfRooms() {
    return this.rooms.size();
  }

}
