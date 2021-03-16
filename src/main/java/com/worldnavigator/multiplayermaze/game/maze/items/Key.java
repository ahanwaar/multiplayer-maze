package com.worldnavigator.multiplayermaze.game.maze.items;

import com.fasterxml.jackson.annotation.JsonIgnore;


public enum Key implements Item {
    COPPER {
        @Override
        public String toString() {
            return "copper";
        }

        @Override
        public String getName() {
            return "copper key";
        }

        @Override
        public String accept(ItemVisitor itemVisitor) {
            return itemVisitor.visitKey(Key.COPPER);
        }
    },
    SILVER {
        @Override
        public String getName() {
            return "silver key";
        }

        @Override
        public String accept(ItemVisitor itemVisitor) {
            return itemVisitor.visitKey(Key.SILVER);
        }

        @Override
        public String toString() {
            return "silver";
        }
    },
    GOLD {
        @Override
        public String getName() {
            return "gold key";
        }

        @Override
        public String accept(ItemVisitor itemVisitor) {
            return itemVisitor.visitKey(Key.GOLD);
        }

        @Override
        public String toString() {
            return "gold";
        }
    },

    NULL {
        @JsonIgnore
        @Override
        public String getName() {
            return "null";
        }

        @Override
        public String accept(ItemVisitor itemVisitor) {
            return itemVisitor.visitKey(Key.NULL);
        }

        @Override
        public String toString() {
            return "null";
        }
    };
}
