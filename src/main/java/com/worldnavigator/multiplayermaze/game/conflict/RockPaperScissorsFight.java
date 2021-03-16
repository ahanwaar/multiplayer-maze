package com.worldnavigator.multiplayermaze.game.conflict;


import com.worldnavigator.multiplayermaze.game.player.Player;
import com.worldnavigator.multiplayermaze.game.player.PlayerStatus;

import java.util.*;

public class RockPaperScissorsFight {

    private final Map<Player, Hand> hands;


    public RockPaperScissorsFight(
            Deque<Player> players) {
        hands = new HashMap<>();
        this.hands.put(players.getFirst(), Hand.NULL);
        this.hands.put(players.getLast(), Hand.NULL);
        players.getFirst().setPlayerStatus(PlayerStatus.FIGHTING);
        players.getLast().setPlayerStatus(PlayerStatus.FIGHTING);
        players.getLast().getGame().addFight(this);
    }

    public boolean start(Player player, Hand hand) {
        if (hands.get(player) == Hand.NULL) {
            hands.put(player, hand);
            return true;
        }
        return false;
    }

    public ConflictStatus getStatus(Player player) {
        Objects.requireNonNull(player);

        Player opponent = player.getCurrentRoom().getPlayers().getFirst();
        Hand playerHand = hands.get(player);
        Hand opponentHand = hands.get(opponent);

        if (playerHand == opponentHand) {
            return ConflictStatus.TIE;
        }

        if (playerHand.beats(opponentHand)) {
            return ConflictStatus.WON;
        } else {
            return ConflictStatus.LOST;
        }
    }

    public void reset() {
        hands.replaceAll((player, hand) -> Hand.NULL);
    }


    public Set<Player> getPlayers() {
        return hands.keySet();
    }

}

