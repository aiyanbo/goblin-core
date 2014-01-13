package org.goblin.exception;

/**
 * Component:
 * Description:
 * Date: 14-1-13
 *
 * @author Andy Ai
 */
public class NoSuchContextException extends Exception {
    public NoSuchContextException(String message) {
        super(message);
    }

    public NoSuchContextException(String message, Throwable cause) {
        super(message, cause);
    }
}
