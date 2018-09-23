/*
 * Created on Mar 15, 2005
 */
package com.osp.sape.Exceptions;

/**
 * 
 * @author Andres Aristizabal
 */
public class SapeDataException extends Exception {

	private static final long serialVersionUID = 1L;

    public SapeDataException() {
        super();
    }

    /**
     * @param message
     */
    public SapeDataException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SapeDataException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public SapeDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
