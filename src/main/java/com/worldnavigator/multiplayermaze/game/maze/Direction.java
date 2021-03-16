package com.worldnavigator.multiplayermaze.game.maze;

public enum Direction {
  NORTH {
    @Override
    public Direction turnRight() {
      return EAST;
    }

    @Override
    public Direction turnLeft() {
      return WEST;
    }

    @Override
    public Direction getOppositeDir() {
      return SOUTH;
    }
  },
  EAST {
    @Override
    public Direction turnRight() {
      return SOUTH;
    }

    @Override
    public Direction turnLeft() {
      return NORTH;
    }

    @Override
    public Direction getOppositeDir() {
      return WEST;
    }
  },
  WEST {
    @Override
    public Direction turnRight() {
      return NORTH;
    }

    @Override
    public Direction turnLeft() {
      return SOUTH;
    }

    @Override
    public Direction getOppositeDir() {
      return EAST;
    }
  },
  SOUTH {
    @Override
    public Direction turnRight() {
      return WEST;
    }

    @Override
    public Direction turnLeft() {
      return EAST;
    }

    @Override
    public Direction getOppositeDir() {
      return NORTH;
    }
  };

  public abstract Direction turnRight();

  public abstract Direction turnLeft();

  public abstract Direction getOppositeDir();
}
