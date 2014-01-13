package org.goblin.dto;

import org.goblin.type.ContextType;

/**
 * Component:
 * Description:
 * Date: 14-1-13
 *
 * @author Andy Ai
 */
public class CommandContext {
    private String context;
    private ContextType type;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public ContextType getType() {
        return type;
    }

    public void setType(ContextType type) {
        this.type = type;
    }
}
