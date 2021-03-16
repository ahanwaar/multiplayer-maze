package com.worldnavigator.multiplayermaze.game.commands.navigation;

import com.worldnavigator.multiplayermaze.game.commands.Command;
import com.worldnavigator.multiplayermaze.game.conflict.ConflictHandler;
import com.worldnavigator.multiplayermaze.game.conflict.ConflictStatus;
import com.worldnavigator.multiplayermaze.game.conflict.RockPaperScissorsFight;
import com.worldnavigator.multiplayermaze.game.maze.Room;
import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects.Door;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

@Component
public class MoveForwardCommand implements Command {

  @Override
  public boolean checkAvailability(Player player) {
    return player.getCurrentWall() instanceof Door
        && player.getPlayerStatus() == PlayerStatus.WALKING;
  }

  @Override
  public String execute(Player player, String... args) {
    Wall wall = player.getCurrentWall();

    if (wall instanceof Door) {
      Door door = (Door) wall;
      if (!door.getLock().isLocked()) {
        if (door.isOpen()) {
          if (door.isMagical()) {
            player.getGame().setWinner(player);
            return "You won! you entered a magical door.";
          }
          moveThroughDoor(door, player);
        }
      }
      return "The door is locked, you need to unlock it first";
    }
    return "You can't move, you are not in front a door";
  }

  public String moveThroughDoor(Door door, Player player) {
    Room currentRoom = player.getCurrentRoom();
    Room nextRoom = door.getNextRoom(currentRoom);
    synchronized (nextRoom) {
      if (nextRoom.isCrowded()) {
        return "You can't enter the room, there is a fight.";
      }
      currentRoom.removePlayer(player);
      return enterUnCrowdedRoom(player, nextRoom);
    }
  }

  public String enterUnCrowdedRoom(Player player, Room nextRoom) {
    nextRoom.addPlayer(player);
    player.setDirection(player.getDirection());
    player.setRoomIndex(nextRoom.getIndex());
    if (nextRoom.isEmpty()) {
      return "You are in the next room.";
    }
    return startConflict(ConflictHandler.getInstance(nextRoom.getPlayers()));
  }

  public String startConflict(ConflictHandler conflictHandler) {
    Player player = conflictHandler.getAssaulterPlayer();

    if (conflictHandler.getPrimaryFightStatus() == ConflictStatus.WON) {
      return "You won by your inventory gold value, you occupied the room.";
    }

    if (conflictHandler.getPrimaryFightStatus() == ConflictStatus.LOST) {
      return "You lost; your opponent inventory level is higher than yours.";
    }

    RockPaperScissorsFight fight = new RockPaperScissorsFight(player.getCurrentRoom().getPlayers());
    player.getGame().addFight(fight);
    return "The room is occupied by another player, "
        + "you need to play rock paper scissor game with him!";
  }

  @Override
  public String name() {
    return "moveForward";
  }

  @Override
  public String getDescription() {
    return "use this command to move forward through open doors.";
  }
}
