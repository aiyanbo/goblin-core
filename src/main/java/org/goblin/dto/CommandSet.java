package org.goblin.dto;

import java.util.List;

/**
 * Component:
 * Description:
 * Date: 13-12-27
 *
 * @author Andy Ai
 */
public class CommandSet {
    private String namespace;
    private List<Command> commands;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
