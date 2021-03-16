package com.worldnavigator.multiplayermaze.game.mazegenerator;

import com.worldnavigator.multiplayermaze.game.maze.Direction;
import com.worldnavigator.multiplayermaze.game.maze.Inventory;
import com.worldnavigator.multiplayermaze.game.maze.Maze;
import com.worldnavigator.multiplayermaze.game.maze.Room;
import com.worldnavigator.multiplayermaze.game.maze.items.Flashlight;
import com.worldnavigator.multiplayermaze.game.maze.items.Gold;
import com.worldnavigator.multiplayermaze.game.maze.items.Key;
import com.worldnavigator.multiplayermaze.game.maze.wall.Lock;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.*;

import java.util.*;

public class MazeGenerator {
    private static final int MIN_ROOMS_NUM = 30;
    private static final int MIN_MAGICAL_DOORS = 5;
    private static Map<Character, Double> wallsProbability;
    private static Map<Character, Double> itemsProbability;

    static {
        wallsProbability = new HashMap<>();
        wallsProbability.put('M', 0.15);
        wallsProbability.put('R', 0.30);
        wallsProbability.put('C', 0.30);
        wallsProbability.put('S', 0.30);
        wallsProbability.put('P', 0.30);

        itemsProbability = new HashMap<>();
        itemsProbability.put('F', 0.50);
        itemsProbability.put('G', 0.35);

    }

    private Stack<Key> mazeKeys = new Stack<>();
    private Map<String, Room> occupiedPositions = new HashMap<>();
    private int numberOfRooms;
    private int roomIndex = 0;
    private int numberOfMagicalDoors = 0;

    public  Maze generateMaze(int playersNum) {
        numberOfRooms = Math.max(MIN_ROOMS_NUM, playersNum);
        Room root = Room.createEmptyRoom(roomIndex, getRandomBoolean(0.3));
        mazeMapper(root, "0,0"); //Create a Skeleton of doors and rooms maze
        mazeInitializer(); //Occupy each room in the skeleton with game entities.
        return new Maze(new ArrayList<>(occupiedPositions.values()));
    }

    private void mazeMapper(Room root, String rootPosition) {
        occupiedPositions.put(rootPosition, root);
        List<Direction> emptyDirections = root.getEmptySides();

        if ((numberOfRooms == occupiedPositions.size()) || (emptyDirections == null)) {
            return;
        }

        boolean isReachable = root.hasDoor();
        for (Direction direction : emptyDirections) {
            if ((root.getWall(direction) instanceof PlainWall) && (!isReachable || getRandomBoolean(0.50))) {
                String newPosition = positionMoveInDirection(rootPosition, direction);
                Room target = null;
                if (occupiedPositions.get(newPosition) != null) {
                    target = (Room) occupiedPositions.get(newPosition);
                    createDoorBetween(root, target, direction);
                } else if ((numberOfRooms != occupiedPositions.size())) {
                    roomIndex += 1;
                    target = Room.createEmptyRoom(roomIndex, getRandomBoolean(0.7));
                    createDoorBetween(root, target, direction);
                    mazeMapper(target, newPosition);
                }
            }
        }
        return;
    }

    private void mazeInitializer() {
        List<Room> rooms = new ArrayList(occupiedPositions.values());
        Collections.shuffle(rooms);
        List<Direction> emptyDirections;
        for (Room currentRoom : rooms) {
            emptyDirections = currentRoom.getEmptySides();
            boolean placedMagicalDoor = false;
            for (Direction direction : emptyDirections) {
                if ((numberOfMagicalDoors < MIN_MAGICAL_DOORS) && !placedMagicalDoor) {
                    Lock lock = new Lock(Key.GOLD);
                    mazeKeys.push(Key.GOLD);
                    numberOfMagicalDoors += 1;
                    placedMagicalDoor = true;
                    currentRoom.setSide(Door.createMagicalDoor(currentRoom, lock), direction);
                } else {
                    currentRoom.setSide(createRandomWall(currentRoom, direction), direction);
                }
            }
        }
        return;
    }

    private Wall createRandomWall(Room source, Direction direction) {
        Wall newWall = new PlainWall();
        for (Map.Entry<Character, Double> entry : wallsProbability.entrySet()) {
            if (getRandomBoolean(entry.getValue())) {
                switch (entry.getKey()) {
                    case 'M': // Create a magical door.
                        Lock lock = new Lock(Key.GOLD);
                        mazeKeys.push(Key.GOLD);
                        newWall = Door.createMagicalDoor(source, lock);
                        numberOfMagicalDoors += 1;
                        break;
                    case 'P': // Create a Painting or a Mirror.
                    case 'R':
                        Key currentKey = Key.NULL;
                        if (!mazeKeys.empty()) {
                            currentKey = mazeKeys.pop();
                        }
                        if (entry.getKey().equals('R')) {
                            newWall = new Mirror(currentKey);
                        } else {
                            newWall = new Painting(currentKey);
                        }
                        break;
                    case 'S': // Create a Seller.
                        newWall = new Seller(null);
                        break;
                    case 'C': // Create a Chest.
                        Inventory inventory = new Inventory();
                        Key chestKey = Key.NULL;
                        if (!mazeKeys.empty()) {
                            chestKey = mazeKeys.pop();
                        }
                        inventory.addItem(chestKey);
                        if (getRandomBoolean(itemsProbability.get('G'))) {
                            inventory.addItem(new Gold((int) (Math.random() * 9) + 1));
                        }
                        if (getRandomBoolean(itemsProbability.get('F'))) {
                            inventory.addItem(new Flashlight());
                        }
                        newWall = new Chest(new Lock(createNonMagicalKey()), inventory);
                        break;
                    default:
                        break;
                }
                break;
            }
        }
        return newWall;
    }

    private void createDoorBetween(Room source, Room sink, Direction direction) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(source);
        rooms.add(sink);
        Key currentKey = createNonMagicalKey();
        Door sourceDoor = new Door(rooms, new Lock(currentKey));
        Collections.reverse(rooms);
        Door sinkDoor = new Door(rooms, new Lock(currentKey));
        source.setSide(sourceDoor, direction);
        sink.setSide(sinkDoor, direction.getOppositeDir());
    }

    private Key createNonMagicalKey() {
        List<Key> keys = Arrays.asList(Key.SILVER, Key.COPPER, Key.NULL);
        Collections.shuffle(keys);
        mazeKeys.push(keys.get(0));
        return keys.get(0);
    }

    public String positionMoveInDirection(String currentPosition, Direction newDirection) {
        String[] position = currentPosition.split(",");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        switch (newDirection) {
            case EAST:
                x = x + 1;
                break;
            case WEST:
                x = x - 1;
                break;
            case SOUTH:
                y = y - 1;
                break;
            case NORTH:
                y = y + 1;
                break;
            default:
        }
        return String.valueOf(x).concat(",").concat(String.valueOf(y));
    }

    private boolean getRandomBoolean(double truthProbability) {
        return Math.random() < truthProbability;
    }
}
