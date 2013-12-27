package org.goblin.executor.impl;

import org.goblin.executor.AbstractCommandExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class BashCommandExecutorImpl extends AbstractCommandExecutor {
    @Override
    protected List<String> createCommands() {
        List<String> commands = new ArrayList<>(5);
        commands.add("bash");
        commands.add("-c");
        return commands;
    }
}
