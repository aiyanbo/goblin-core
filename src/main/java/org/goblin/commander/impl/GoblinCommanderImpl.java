package org.goblin.commander.impl;

import org.goblin.commander.GoblinCommander;
import org.goblin.dto.ProcessContext;
import org.goblin.exception.CommandExecuteException;
import org.goblin.executor.CommandExecutor;
import org.goblin.executor.impl.BashCommandExecutorImpl;
import org.goblin.executor.impl.WindowsCommandExecutorImpl;
import org.jmotor.util.SystemUtilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class GoblinCommanderImpl implements GoblinCommander {
    private static final Map<String, CommandExecutor> COMMANDER_MAPPER = new HashMap<>(3);

    static {
        COMMANDER_MAPPER.put("windows", new WindowsCommandExecutorImpl());
        BashCommandExecutorImpl bashCommandExecutor = new BashCommandExecutorImpl();
        COMMANDER_MAPPER.put("mac os x", bashCommandExecutor);
        COMMANDER_MAPPER.put("linux", bashCommandExecutor);
    }

    @Override
    public ProcessContext execute(ProcessContext context, String command) throws CommandExecuteException {
        return switchCommandExecutor().execute(context, command);
    }

    private CommandExecutor switchCommandExecutor() {
        return COMMANDER_MAPPER.get(SystemUtilities.getOSName().toLowerCase());
    }
}
