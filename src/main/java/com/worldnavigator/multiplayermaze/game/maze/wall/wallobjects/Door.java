package com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.worldnavigator.multiplayermaze.game.maze.Room;
import com.worldnavigator.multiplayermaze.game.maze.wall.Lock;
import com.worldnavigator.multiplayermaze.game.maze.wall.Lockable;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.WallVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Door extends Wall implements Lockable {

  @JsonIgnore private List<Room> connectedRooms;
  private Lock lock;
  private boolean open;

  @JsonCreator
  public Door(List<Room> connectedRooms, Lock lock) {
    this.connectedRooms = connectedRooms;
    this.lock = lock;
    this.open = false;
  }

  public static Door createMagicalDoor(Room room, Lock lock) {
    List<Room> rooms = new ArrayList<>();
    rooms.add(room);
    return new Door(rooms, lock);
  }

  public boolean isMagical() {
    return this.connectedRooms.size() < 2;
  }

  public boolean open() {
    if (!lock.isLocked()) {
      open = true;
    }
    return open;
  }

  public boolean isOpen() {
    return open;
  }

  public Room getNextRoom(Room room) {
    int index = connectedRooms.indexOf(room);
    if (index == 1) {
      return connectedRooms.get(0);
    }
    return connectedRooms.get(1);
  }

  public List<Integer> getRoomsIndices() {
    return connectedRooms.stream().map(Room::getIndex).collect(Collectors.toList());
  }

  @Override
  public String accept(WallVisitor visitor) {
    return visitor.visitDoor(this);
  }

  @Override
  public String toString() {
    return "Door{" + "lock=" + lock + ", open=" + open + ", magical=" + isMagical() + '}';
  }

  @Override
  public String getName() {
    return "door";
  }
}
