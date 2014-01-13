package org.goblin.parser;

import org.goblin.dto.CommandContext;
import org.goblin.exception.NoSuchContextException;

/**
 * Component:
 * Description:
 * Date: 14-1-8
 *
 * @author Andy Ai
 */
public interface ContextParser {
    CommandContext parse(String commandline) throws NoSuchContextException;
}
