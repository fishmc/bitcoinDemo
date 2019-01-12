package com.lanjing.bitcoin.utils;


import com.lanjing.bitcoin.exception.E;
import com.lanjing.bitcoin.exception.WalletException;

public class AssertUp {
    public static void isTrue(boolean what, E e){
        if(!what){
            throw new WalletException(e);
        }
    }

    public static void isFalse(boolean what, E e) {
        if (what) {
            throw new WalletException(e);
        }
    }

    public static void isFalse(boolean what, String e) {
        if (what) {
            throw new WalletException(e);
        }
    }
}
