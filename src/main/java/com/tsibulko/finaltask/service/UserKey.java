package com.tsibulko.finaltask.service;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserKey {
    private static UserKey instance;
    private static Lock lock = new ReentrantLock();

    private Map<Integer, String> keys = new WeakHashMap<>();

    private UserKey() {
    }

    public static UserKey getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new UserKey();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public void add(Integer id, String key) {
        keys.put(id, key);
    }

    public void remove(Integer id, String key) {
        keys.remove(id);
    }

    public String get(Integer id) {
        return keys.get(id);
    }

}
