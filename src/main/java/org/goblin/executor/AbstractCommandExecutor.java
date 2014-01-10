package org.goblin.executor;

import org.goblin.dto.Executable;
import org.goblin.dto.ProcessContext;
import org.goblin.exception.CommandExecuteException;
import org.jmotor.util.StringUtilities;
import org.jmotor.util.SystemUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public abstract class AbstractCommandExecutor implements CommandExecutor {
    @Override
    public Process execute(ProcessContext context, Executable executable) throws CommandExecuteException {
        List<String> commands = createCommands();
        commands.add(StringUtilities.join(Arrays.asList(new String[]{
                executable.getCommand(), executable.getOptions(), executable.getContext()
        }), StringUtilities.BLANK_SPACE));
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        if (StringUtilities.isBlank(context.getDirectory())) {
            processBuilder.directory(new File(SystemUtilities.getUserDir()));
        } else {
            processBuilder.directory(new File(context.getDirectory()));
        }
        try {
            return processBuilder.start();
        } catch (IOException e) {
            throw new CommandExecuteException(e.getLocalizedMessage(), e);
        }
    }

    protected abstract List<String> createCommands();
}
