package org.goblin.parser.impl;

import org.goblin.dto.Command;
import org.goblin.dto.CommandSet;
import org.goblin.exception.CommandNotFoundException;
import org.goblin.parser.CommandParser;
import org.goblin.provider.CommandSetProvider;
import org.jmotor.util.CollectionUtilities;
import org.jmotor.util.StringUtilities;
import org.jmotor.util.SystemUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Component:
 * Description:
 * Date: 13-12-31
 *
 * @author Andy Ai
 */
public class CommandParserImpl implements CommandParser {
    private CommandSetProvider commandSetProvider;

    @Override
    public String parse(String command) throws CommandNotFoundException {
        if (StringUtilities.isBlank(command)) {
            throw new NullPointerException("Command can't be empty.");
        }
        List<CommandSet> commandSets = commandSetProvider.getCommandSets();
        if (CollectionUtilities.isEmpty(commandSets)) {
            throw new CommandNotFoundException("Can't found command: " + command);
        }
        String[] _commands = StringUtilities.split(command, StringUtilities.BLANK_SPACE);
        Command cmd = null;
        int startIndex = 1;
        for (int i = 0; i < _commands.length; i++) {
            startIndex = i + 1;
            cmd = findCommand(commandSets, StringUtilities.join(Arrays.copyOf(_commands, startIndex),
                    StringUtilities.BLANK_SPACE));
            if (cmd != null) {
                break;
            }
        }
        if (null == cmd) {
            throw new CommandNotFoundException("Can't found command: " + command);
        }
        String osName = SystemUtilities.getOSName().toLowerCase();
        List<String> results = new ArrayList<>(_commands.length);
        String shell = cmd.getMapping().get(osName);
        if (StringUtilities.isBlank(shell)) {
            shell = cmd.getMapping().get("all");
        }
        results.add(shell);
        for (int i = startIndex; i < _commands.length; i++) {
            String context = cmd.getContext().get(_commands[i]);
            if (StringUtilities.isNotBlank(context)) {
                results.add(context);
            } else {
                results.add(_commands[i]);
            }
        }
        return StringUtilities.join(results, StringUtilities.BLANK_SPACE);
    }

    private Command findCommand(List<CommandSet> commandSets, String commandStr) {
        for (CommandSet commandSet : commandSets) {
            List<Command> commands = commandSet.getCommands();
            if (CollectionUtilities.isEmpty(commands)) {
                continue;
            }
            for (Command command : commands) {
                if (command.getName().equals(commandStr)) {
                    return command;
                }
            }
        }
        return null;
    }

    public void setCommandSetProvider(CommandSetProvider commandSetProvider) {
        this.commandSetProvider = commandSetProvider;
    }
}
