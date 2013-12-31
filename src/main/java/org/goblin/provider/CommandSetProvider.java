package org.goblin.provider;

import org.goblin.dto.CommandSet;
import org.goblin.exception.CommandSetParseException;

import java.io.InputStream;
import java.util.List;

/**
 * Component:
 * Description:
 * Date: 13-12-31
 *
 * @author Andy Ai
 */
public interface CommandSetProvider {
    void register(String name) throws CommandSetParseException;

    void register(InputStream inputStream) throws CommandSetParseException;

    List<CommandSet> getCommandSets();
}
