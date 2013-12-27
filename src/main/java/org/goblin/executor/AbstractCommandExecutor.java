package org.goblin.executor;

import org.goblin.dto.ProcessContext;
import org.goblin.exception.CommandExecuteException;
import org.jmotor.util.StringUtilities;
import org.jmotor.util.SystemUtilities;

import java.io.File;
import java.io.IOException;
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
    public ProcessContext execute(ProcessContext context, String command) throws CommandExecuteException {
        List<String> commands = createCommands();
//        commands.addAll(Arrays.asList(StringUtilities.split(command, StringUtilities.BLANK_SPACE)));
        commands.add(command);
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        if (StringUtilities.isBlank(context.getDirectory())) {
            processBuilder.directory(new File(SystemUtilities.getUserDir()));
        } else {
            processBuilder.directory(new File(context.getDirectory()));
        }
        try {
            Process process = processBuilder.start();
            context.setProcess(process);
        } catch (IOException e) {
            throw new CommandExecuteException(e.getLocalizedMessage(), e);
        }
        return context;
    }

    protected abstract List<String> createCommands();
}
