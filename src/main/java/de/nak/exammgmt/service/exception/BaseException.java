/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.exception;

/**
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
// TODO name?
public class BaseException extends Exception {

    private int code; // FIXME nullable?

    public BaseException(int code) {
        this.code = code;
    }

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    @Override
    public String toString() {
        return super.toString() + " (code " + code + ")";
    }

    public int getCode() {
        return code;
    }
}
