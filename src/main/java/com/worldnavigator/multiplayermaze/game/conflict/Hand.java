package com.worldnavigator.multiplayermaze.game.conflict;

public enum Hand {
    ROCK {
        @Override
        public boolean beats(Hand hand) {
            return hand == SCISSOR;
        }
    },
    PAPER {
        @Override
        public boolean beats(Hand hand) {
            return hand == ROCK;
        }
    },
    SCISSOR {
        @Override
        public boolean beats(Hand hand) {
            return hand == PAPER;
        }
    }, NULL {
        public boolean beats(Hand hand) {
            return false;
        }
    };

    public abstract boolean beats(Hand hand);
}
