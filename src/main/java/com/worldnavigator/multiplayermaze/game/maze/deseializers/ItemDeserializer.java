package com.worldnavigator.multiplayermaze.game.maze.deseializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.worldnavigator.multiplayermaze.game.exceptions.NoSuchItemException;
import com.worldnavigator.multiplayermaze.game.maze.items.Item;
import com.worldnavigator.multiplayermaze.game.maze.items.ItemFactory;


import java.io.IOException;

public class ItemDeserializer extends JsonDeserializer<Item> {

  @Override
  public Item deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    try {
      JsonNode node = jsonParser.getCodec().readTree(jsonParser);
      return ItemFactory.getItem(node.textValue());

    } catch (NoSuchItemException e) {
      throw new IOException(e);
    }
  }
}
