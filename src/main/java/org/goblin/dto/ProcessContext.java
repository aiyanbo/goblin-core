package org.goblin.dto;

import java.util.List;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class ProcessContext {
    private String next;
    private String search;
    private String directory;
    private List<String> commands;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
