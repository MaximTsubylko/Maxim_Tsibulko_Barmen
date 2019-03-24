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
    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            LOGGER.info("Object " + JDBCConnectionPool.getInstance() + " loaded in memory.");
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            LOGGER.info("JDBCConnectionPool destroy.");
            JDBCConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
        }

    }
}