package com.worldnavigator.multiplayermaze.game.maze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.Door;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.PlainWall;
import com.worldnavigator.multiplayermaze.game.player.Player;

import java.util.*;

public class Room {

    private final int index;
    private final boolean hasLights;
    private final Deque<Player> players;

    @JsonDeserialize(as = EnumMap.class)
    private Map<Direction, Wall> sides;

    private boolean lit;

    @JsonCreator
    private Room(
            int index,
            boolean hasLights,
            Map<Direction, Wall> sides
    ) {
        this.index = index;
        this.hasLights = hasLights;
        this.lit = false;
        this.sides = Objects.requireNonNull(sides);
        this.players = new ArrayDeque<>();
    }

    public static Room createEmptyRoom(int index, boolean hasLights) {
        Map<Direction, Wall> wallMap = new HashMap<>();
        wallMap.put(Direction.NORTH, new PlainWall());
        wallMap.put(Direction.SOUTH, new PlainWall());
        wallMap.put(Direction.EAST, new PlainWall());
        wallMap.put(Direction.WEST, new PlainWall());
        return new Room(index, hasLights, wallMap);
    }

    @JsonIgnore
    public List<Direction> getEmptySides() {
        Map<Direction, Wall> plainWalls = new HashMap<>();
        for (Direction dir : sides.keySet()) {
            if (sides.get(dir) instanceof PlainWall) {
                plainWalls.put(dir, new PlainWall());
            }
        }
        return new ArrayList<>(plainWalls.keySet());
    }

    public boolean hasDoor() {
        if (sides.values().stream().anyMatch(wall -> wall instanceof Door)) return true;
        return false;

    }

    public int getIndex() {
        return index;
    }

    public Map<Direction, Wall> getSides() {
        return sides;
    }

    public void setSides(Map<Direction, Wall> walls) {
        this.sides = walls;
    }

    public void setSide(Wall wall, Direction direction) {
        sides.put(direction, wall);
    }

    public Wall getWall(Direction direction) {
        return this.sides.get(direction);
    }

    public void switchLights() {
        this.lit = !this.lit;
    }

    public boolean hasLights() {
        return hasLights;
    }

    public boolean isLit() {
        return this.lit;
    }

    public Deque<Player> getPlayers() {
        return players;
    }

    public boolean isCrowded() {
        return this.players.size() == 2;
    }

    public boolean isEmpty() {
        return this.players.size() == 0;
    }

    public void addPlayer(Player player) {
        if (!isCrowded()) {
            this.players.addLast(player);
        }
    }

    public void removePlayer(Player player) {
        if (players.getFirst().equals(player)) {
            players.removeFirst();
        } else if (players.getLast().equals(player)) {
            players.removeLast();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return index == room.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public String toString() {
        return "Room{"
                + "index="
                + index
                + ", hasLights="
                + hasLights
                + ", players="
                + players
                + ", sides="
                + sides
                + ", lit="
                + lit
                + '}';
    }
}
