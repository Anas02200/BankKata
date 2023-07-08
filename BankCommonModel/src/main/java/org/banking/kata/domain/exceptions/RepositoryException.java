package org.banking.kata.domain.exceptions;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super(message);
    }
}
