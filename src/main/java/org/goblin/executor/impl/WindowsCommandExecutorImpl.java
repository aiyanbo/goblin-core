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
public class WindowsCommandExecutorImpl extends AbstractCommandExecutor {
    @Override
    protected List<String> createCommands() {
        List<String> commands = new ArrayList<>(5);
        commands.add("cmd");
        commands.add("/c");
        return commands;
    }
}
