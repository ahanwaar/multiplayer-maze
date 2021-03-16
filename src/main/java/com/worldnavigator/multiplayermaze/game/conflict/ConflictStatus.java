package com.worldnavigator.multiplayermaze.game.conflict;

public enum ConflictStatus {
    TIE {
        @Override
        public String getMessage() {
            return "There is a Tie, try again!";
        }
    },
    LOST {
        @Override
        public String getMessage() {
            return "you lost, Game is Over!";
        }
    },
    WON {
        @Override
        public String getMessage() {
            return "You won the fight!";
        }
    };

    public abstract String getMessage();

}
