package org.goblin.parser;

import org.goblin.exception.CommandNotFoundException;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public interface CommandParser {
    String parse(String command) throws CommandNotFoundException;
}
