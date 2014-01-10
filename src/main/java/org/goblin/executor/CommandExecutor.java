package org.goblin.executor;

import org.goblin.dto.Executable;
import org.goblin.dto.ProcessContext;
import org.goblin.exception.CommandExecuteException;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public interface CommandExecutor {
    Process execute(ProcessContext context, Executable executable) throws CommandExecuteException;
}
