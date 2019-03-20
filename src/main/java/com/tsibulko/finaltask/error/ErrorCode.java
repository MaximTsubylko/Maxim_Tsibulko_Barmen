package com.tsibulko.finaltask.error;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ErrorCode {
    private String err_code;
    private static ErrorCode instance;
    private static Lock lock = new ReentrantLock();

    public static ErrorCode getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ErrorCode();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    private ErrorCode() {
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

}
