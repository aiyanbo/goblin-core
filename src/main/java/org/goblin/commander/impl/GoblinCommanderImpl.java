package org.goblin.commander.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.goblin.commander.GoblinCommander;
import org.goblin.dto.Executable;
import org.goblin.dto.ProcessContext;
import org.goblin.dto.Result;
import org.goblin.exception.CommandExecuteException;
import org.goblin.exception.CommandNotFoundException;
import org.goblin.executor.CommandExecutor;
import org.goblin.parser.CommandParser;
import org.jmotor.util.StringUtilities;
import org.jmotor.util.SystemUtilities;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class GoblinCommanderImpl implements GoblinCommander {
    private static final String PARENT_SYMBOL = "..";
    private CommandParser commandParser;
    private CommandExecutor commandExecutor;


    @Override
    public Result execute(ProcessContext processContext, String command) {
        Result result = new Result();
        try {
            if (StringUtilities.isNotBlank(processContext.getSearch())) {
                boolean confirm = BooleanUtils.toBoolean(command);
                if (confirm) {
                    result.setSearch(processContext.getSearch());
                } else {
                    result.setSpeech("Canceled");
                }
                processContext.setSearch(null);
                return result;
            }
            Executable executable = commandParser.parse(command);
            String context = executable.getContext();
            if (StringUtilities.isNotBlank(context)) {
                boolean passed = validateContext(executable);
                if (!passed) {
                    result.setException("Can not found context");
                    result.setSpeech("Can not found" + context);
                }
            }
            if (StringUtilities.isBlank(executable.getCommand())) {
                result.setSpeech(executable.getResponses().get(0));
                return result;
            }
            Process process = commandExecutor.execute(processContext, executable);
            result.setProcess(process);
            if (executable.getCommand().equals("cd")) {
                String options = executable.getOptions();
                if (StringUtilities.isNotBlank(options) && PARENT_SYMBOL.equals(options.trim())) {
                    String directory = processContext.getDirectory();
                    if (StringUtilities.isBlank(directory)) {
                        directory = SystemUtilities.getUserDir();
                    }
                    int endIndex = directory.lastIndexOf(File.separator);
                    if (endIndex > 0) {
                        directory = directory.substring(0, endIndex);
                        processContext.setDirectory(directory);
                    }
                } else {
                    processContext.setDirectory(context);
                }
            }
            return result;
        } catch (CommandNotFoundException | CommandExecuteException e) {
            processContext.setSearch(command);
            result.setSpeech("Do you want search on website?");
            return result;
        }
    }

    public boolean validateContext(Executable executable) {
        if (null == executable.getContextType()) {
            return Files.exists(FileSystems.getDefault().getPath(executable.getContext()));
        }
        switch (executable.getContextType()) {
            case "file":
                return Files.exists(FileSystems.getDefault().getPath(executable.getContext()));
            default:
                return Files.exists(FileSystems.getDefault().getPath(executable.getContext()));
        }
    }

    public void setCommandParser(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }
}
