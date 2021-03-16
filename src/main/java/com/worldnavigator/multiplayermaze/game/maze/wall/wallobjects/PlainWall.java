package com.worldnavigator.multiplayermaze.game.maze.wall.wallobjects;


import com.worldnavigator.multiplayermaze.game.maze.wall.Wall;
import com.worldnavigator.multiplayermaze.game.maze.wall.WallVisitor;

public class PlainWall extends Wall {

    @Override
    public String accept(WallVisitor visitor) {
        return visitor.visitPlainWall(this);
    }

    @Override
    public String toString() {
        return "plain wall";
    }

    @Override
    public String getName() {
        return "plain wall";
    }
}
