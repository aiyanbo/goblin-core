package org.goblin.commander;

import org.goblin.dto.ProcessContext;
import org.goblin.dto.Result;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public interface GoblinCommander {
    Result execute(ProcessContext processContext, String command);
}
