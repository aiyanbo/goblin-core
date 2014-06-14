package org.goblin.parser.impl;

import org.goblin.dto.Command;
import org.goblin.dto.CommandSet;
import org.goblin.dto.Executable;
import org.goblin.exception.CommandNotFoundException;
import org.goblin.parser.CommandParser;
import org.goblin.provider.CommandSetProvider;
import org.jmotor.util.CollectionUtilities;
import org.jmotor.util.StringUtilities;
import org.jmotor.util.SystemUtilities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public Executable parse(String commandline) throws CommandNotFoundException {
        if (StringUtilities.isBlank(commandline)) {
            throw new NullPointerException("Command can't be empty.");
        }
        List<CommandSet> commandSets = commandSetProvider.getCommandSets();
        if (CollectionUtilities.isEmpty(commandSets)) {
            throw new CommandNotFoundException("Can't found command: " + commandline);
        }
        String[] _commands = StringUtilities.split(commandline, StringUtilities.BLANK_SPACE);
        Command cmd = null;
        int startIndex;
        for (int i = 0; i < _commands.length; i++) {
            startIndex = i + 1;
            cmd = findCommand(commandSets, StringUtilities.join(Arrays.copyOf(_commands, startIndex),
                    StringUtilities.BLANK_SPACE));
            if (cmd != null) {
                break;
            }
        }
        if (null == cmd) {
            throw new CommandNotFoundException("Can't found commandLine: " + commandline);
        }
        Executable executable = new Executable();
        String osName = SystemUtilities.getOSName().toLowerCase();
        String shell = cmd.getMapping().get(osName);
        if (StringUtilities.isBlank(shell)) {
            shell = cmd.getMapping().get("all");
        }
        String predicate = StringUtilities.remove(commandline, cmd.getName());
        Map<String, String> options = cmd.getOptions();

        Integer contextIndex = cmd.getContextIndex();
        if (null != contextIndex && StringUtilities.isNotBlank(predicate)) {
            String[] contexts = StringUtilities.split(predicate, StringUtilities.BLANK_SPACE);
            String context;
            if (Math.abs(contextIndex) > contexts.length) {
                context = contexts[contexts.length - 1];
            } else {
                if (contextIndex > 0) {
                    context = contexts[contextIndex - 1];
                } else {
                    context = contexts[contexts.length + contextIndex];
                }
            }
            if (CollectionUtilities.isNotEmpty(options) && !options.containsKey(context)) {
                executable.setContext(context);
                predicate = StringUtilities.remove(predicate, context);
            }
        }

        if (StringUtilities.isNotBlank(predicate) && CollectionUtilities.isNotEmpty(options)) {
            String _options = predicate;
            for (Map.Entry<String, String> entry : options.entrySet()) {
                _options = _options.replace(entry.getKey(), entry.getValue());
            }
            executable.setOptions(_options);
        }
        executable.setCommand(shell);
        return executable;
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
