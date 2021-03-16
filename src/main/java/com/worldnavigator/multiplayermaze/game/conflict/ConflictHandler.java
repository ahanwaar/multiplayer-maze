package com.worldnavigator.multiplayermaze.game.conflict;

import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.Objects;

@Component
public class ConflictHandler {

    private Deque<Player> opponents;
    private ConflictStatus conflictStatus;


    private ConflictHandler() {
    }

    public static ConflictHandler getInstance(Deque<Player> opponents) {
        opponents = Objects.requireNonNull(opponents);
        opponents.getFirst().setPlayerStatus(PlayerStatus.FIGHTING);
        opponents.getLast().setPlayerStatus(PlayerStatus.FIGHTING);

        return new ConflictHandler();
    }

    public ConflictStatus getPrimaryFightStatus() {
        Player p1 = opponents.getFirst();
        Player p2 = opponents.getLast();

        if (p1.getTotalGoldValue() > p2.getTotalGoldValue()) {
            setResults(p1, p2);
            conflictStatus = ConflictStatus.LOST;
        } else if (p1.getTotalGoldValue() < p2.getTotalGoldValue()) {
            setResults(p2, p1);
            conflictStatus = ConflictStatus.WON;
        } else {
            conflictStatus = ConflictStatus.TIE;
        }

        return conflictStatus;
    }

    public synchronized String status(Player player, RockPaperScissorsFight fight) {

        switch (fight.getStatus(player)) {
            case WON:
                setResults(player, opponents.getFirst());
                return fight.getStatus(player).getMessage();

            case LOST:
                setResults(opponents.getFirst(), player);
                return fight.getStatus(player).getMessage();

            case TIE:
                fight.reset();
                return fight.getStatus(player).getMessage();

            default:
                return "Something went wrong!";
        }
    }

    public void setResults(Player winner, Player loser) {
        winner.setPlayerStatus(PlayerStatus.WALKING);
        loser.setPlayerStatus(PlayerStatus.LOST);
        winner.getCurrentRoom().removePlayer(loser);
        winner.getGame().removePlayer(loser);
        winner.getGame().distributeGold(loser.getTotalGoldValue());
    }

    public Player getAssaulterPlayer() {
        return opponents.getLast();
    }

}
