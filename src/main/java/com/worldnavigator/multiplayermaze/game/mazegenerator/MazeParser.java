package com.worldnavigator.multiplayermaze.game.mazegenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldnavigator.multiplayermaze.game.maze.Maze;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MazeParser {
    static String parseMaze(int playersNum) throws JsonProcessingException {
        Maze maze = new MazeGenerator().generateMaze(playersNum);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        Objects.requireNonNull(maze, "The maze Object is null");
        File file =
                new File(
                        String.format(
                                "E:\\Atypon project\\last version\\game\\src\\main\\resources\\json\\%s.json",
                                maze.getNumOfRooms() + "roomsMaze"));
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, maze);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = null;
       return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(maze);
    }
}
