package com.worldnavigator.multiplayermaze.game.commands;

import com.worldnavigator.multiplayermaze.game.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Component
public final class CommandInvoker {

    private final ApplicationContext applicationContext;
    private Map<String, Command> commands;

    @Autowired
    public CommandInvoker(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void collect() {
        Map<String, Command> beans = applicationContext.getBeansOfType(Command.class);
        this.commands = beans.values().stream().collect(toMap(Command::name, identity()));
    }

    public String execute(Player player, String line) {

        String[] parts = line.trim().toLowerCase().split("\\s+", 2);

        Command command = commands.get(parts[0]);

        if (command != null) {

            if (command.checkAvailability(player)) {

                String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[]{};

                if (command.validate(player, args)) {
                    return command.execute(player, args);
                } else {
                    return "The arguments you supplied are not valid!";
                }

            } else {
                return String.format("The command (%s) is not available!", parts[0]);
            }
        }

        return String.format("The command (%s) doesn't exists!", parts[0]);
    }

    public List<Command> getAvailableCommands(Player player) {
        return commands.values().stream()
                .filter(command -> command.checkAvailability(player))
                .collect(toList());
    }
}
