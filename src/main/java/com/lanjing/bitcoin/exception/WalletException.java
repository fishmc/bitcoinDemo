package com.lanjing.bitcoin.exception;

public class WalletException extends BaseException {

    public WalletException() {
        this(E.UNKNOWN_ERROR);
    }

    public WalletException(E result) {
        super(result.getCode(), result.getMessage());
    }

    public WalletException(String message) {
        super("W000", message);
    }

    public WalletException(String code, String message) {

    }
}
