package com.tsibulko.finaltask.service.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomMessageFactory {
    private static CustomMessageFactory instance;
    private static final String CONFIRM_PROPS_PATH = "property/confirmmessage.properties";
    private static final String RECOVERY_PROPS_PATH = "property/recoverymessage.properties";
    private static Lock lock = new ReentrantLock();

    private CustomMessageFactory() {
    }

    public static CustomMessageFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new CustomMessageFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public CustomMessage getMessage(CustomMessageType type) {
        switch (type) {
            case CONFIRM:
                return initMessage(CONFIRM_PROPS_PATH);
            case RECOVERY:
                return initMessage(RECOVERY_PROPS_PATH);

        }
        throw new UnsupportedOperationException();
    }

    private static CustomMessage initMessage(String path) {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        CustomMessage message = new CustomMessage();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            properties.load(inputStream);
            message.setSubject(properties.getProperty("subject"));
            message.setText(properties.getProperty("text"));
        } catch (IOException e) {
//
        }
        return message;
    }
}
