package com.configuration.machine.exceptions;

import java.util.NoSuchElementException;

public class OwnerNotFoundException extends NoSuchElementException {

    public OwnerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
