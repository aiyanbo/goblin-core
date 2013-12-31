package org.goblin.commander.impl;

import org.goblin.commander.GoblinCommander;
import org.goblin.dto.ProcessContext;
import org.goblin.exception.CommandExecuteException;
import org.goblin.exception.CommandNotFoundException;
import org.goblin.executor.CommandExecutor;
import org.goblin.parser.CommandParser;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class GoblinCommanderImpl implements GoblinCommander {
    private CommandParser commandParser;
    private CommandExecutor commandExecutor;


    @Override
    public ProcessContext execute(ProcessContext context, String command)
            throws CommandNotFoundException, CommandExecuteException {
        String _command = commandParser.parse(command);
        ProcessContext processContext = commandExecutor.execute(context, _command);
        if (_command.startsWith("cd")) {
            processContext.setDirectory(_command.replace("cd", "").trim());
        }
        return processContext;
    }

    public void setCommandParser(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }
}
