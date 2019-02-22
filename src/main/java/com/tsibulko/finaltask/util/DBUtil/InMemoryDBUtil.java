package com.tsibulko.finaltask.util.DBUtil;

import com.tsibulko.finaltask.dao.AutoConnection;
import com.tsibulko.finaltask.dao.ConnectionPoolFactory;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class InMemoryDBUtil {
    @AutoConnection
    public static void fill() throws DaoException, IOException, SQLException, ConnectionPoolException {
        try (Connection connection = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool()
                .retrieveConnection()) {

            String dataBase = FileUtils.
                    fileRead("src/test/resources/hsqldb/script/creatbase.sql");

            connection
                    .createStatement()
                    .executeUpdate(dataBase);

            String fullTestData = FileUtils
                    .fileRead("src/test/resources/hsqldb/script/setdata.sql");

            connection
                    .createStatement()
                    .executeUpdate(fullTestData);
        }
    }

    @AutoConnection
    public static void drop() throws SQLException, IOException, ConnectionPoolException {
        try (Connection connection = ConnectionPoolFactory
                .getInstance()
                .getConnectionPool()
                .retrieveConnection()) {

            String dataBase = FileUtils.
                    fileRead("src/test/resources/hsqldb/script/drop.sql");

            connection
                    .createStatement()
                    .executeUpdate(dataBase);
            ConnectionPoolFactory.getInstance().getConnectionPool().putBackConnection(connection);
        }
    }
}