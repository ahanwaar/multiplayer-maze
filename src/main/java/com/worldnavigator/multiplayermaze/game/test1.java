package com.worldnavigator.multiplayermaze.game;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldnavigator.multiplayermaze.game.maze.Maze;
import com.worldnavigator.multiplayermaze.game.mazegenerator.MazeGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class test1 {
    public static void main(String[] args) throws JsonProcessingException {
        Maze maze = new MazeGenerator().generateMaze(40);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        Objects.requireNonNull(maze, "The maze Object is null");
        File file =new File(String.format("E:\\multiplayer-maze\\src\\main\\resources\\static\\json\\%s.json","roomsMaze"));
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, maze);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
