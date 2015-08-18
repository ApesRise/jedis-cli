package com.violetgo.jediscli;

/**
 * @author weigao
 * @since 15/8/18
 */
public class JedisCliException extends Exception {
    public JedisCliException(String message) {
        super(message);
    }

    public JedisCliException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
