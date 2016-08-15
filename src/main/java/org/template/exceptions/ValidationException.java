package org.template.exceptions;

import org.slf4j.helpers.MessageFormatter;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Object args) {
        super(MessageFormatter.format(message, args).getMessage());
    }
}