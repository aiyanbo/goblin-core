package org.goblin.exception;

/**
 * Component:
 * Description:
 * Date: 13-12-31
 *
 * @author Andy Ai
 */
public class CommandSetParseException extends Exception {
    public CommandSetParseException(String message) {
        super(message);
    }

    public CommandSetParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
