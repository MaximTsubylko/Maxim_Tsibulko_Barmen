package com.tsibulko.finaltask.listener;

import com.tsibulko.finaltask.dao.ConnectionPoolException;
import com.tsibulko.finaltask.dao.impl.JDBCConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            JDBCConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            JDBCConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }
}