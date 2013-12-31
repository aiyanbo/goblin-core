package org.goblin.exception;

/**
 * Component:
 * Description:
 * Date: 13-12-31
 *
 * @author Andy Ai
 */
public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String message) {
        super(message);
    }

    public CommandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
