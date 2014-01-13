package org.goblin.exception;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class CommandExecuteException extends Exception {
    public CommandExecuteException() {
    }

    public CommandExecuteException(String message) {
        super(message);
    }

    public CommandExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

}
