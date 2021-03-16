package com.worldnavigator.multiplayermaze.game.major;


import com.worldnavigator.multiplayermaze.game.conflict.RockPaperScissorsFight;
import com.worldnavigator.multiplayermaze.game.maze.Direction;
import com.worldnavigator.multiplayermaze.game.maze.Room;
import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class Game {

  private final UUID uuid;
  private final String owner;
  private final GameConfig gameConfig;
  private final LocalDateTime startedAt;
  private Map<String, Player> players;
  private String winner;
  private Map<Player, RockPaperScissorsFight> fightMap;

  public Game(UUID uuid, String owner, GameConfig gameConfig) {
      this.uuid = uuid;
      this.gameConfig = gameConfig;
      this.fightMap = new HashMap<>();
      this.players = new HashMap<>();
      this.startedAt = LocalDateTime.now();
      this.owner = owner;
  }

  public void addPlayer(Player player) {
    player.setGame(this);
    players.put(player.getUserName(), player);
  }

  public void start() {
    List<Room> rooms = gameConfig.getMaze().getRooms();
    Collections.shuffle(rooms);
    players.values().stream().forEach(player -> player.setRoomIndex(rooms.get(0).getIndex()));//TODO
    players.values().stream().forEach(player -> player.setDirection(Direction.NORTH));//TODO
    distributeGold(gameConfig.getInitialGoldAmount());
    players.values().stream().forEach(player -> player.setPlayerStatus(PlayerStatus.WALKING));
  }

  public void distributeGold(int amount) {
    List<Player> currentPlayers =
            this.players.values().stream()
                    .filter(p -> p.getPlayerStatus() != PlayerStatus.LOST)
                    .collect(toList());

    if (currentPlayers.isEmpty()) {
      return;
    }

    int goldForEach = amount / currentPlayers.size();
    currentPlayers.forEach(p -> p.getInventory().getGold().addGoldAmount(goldForEach));
  }

  public void setWinner(Player winner) {
    this.players.forEach((s, p) -> p.setPlayerStatus(PlayerStatus.LOST));
    winner.setPlayerStatus(PlayerStatus.WON);

    this.winner = winner.getUserName();
  }

  public Player getPlayer(String username) {
    Objects.requireNonNull(username);
    Player player = players.get(username);
    return Objects.requireNonNull(player);
  }

  public void removePlayer(Player player) {
    Objects.requireNonNull(player);
    players.remove(player);
  }

  public void addFight(RockPaperScissorsFight fight) {
    Objects.requireNonNull(fight);
    fight.getPlayers().forEach(player -> fightMap.put(player, fight));
  }

  public RockPaperScissorsFight getFightByPlayer(Player player) {
    return fightMap.get(player);
  }

  public boolean isStarted() {
    return LocalDateTime.now().isAfter(startedAt);
  }

  public boolean isFinished() {
    return winner != null || isTimeout();
  }

  public boolean isTimeout() {
    return startedAt.plusMinutes(gameConfig.getTimeOut()).isBefore(LocalDateTime.now());
  }
}
