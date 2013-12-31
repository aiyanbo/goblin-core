package org.goblin.commander;

import org.goblin.dto.ProcessContext;
import org.goblin.exception.CommandExecuteException;
import org.goblin.exception.CommandNotFoundException;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public interface GoblinCommander {
    ProcessContext execute(ProcessContext context, String command) throws CommandNotFoundException, CommandExecuteException;
}
