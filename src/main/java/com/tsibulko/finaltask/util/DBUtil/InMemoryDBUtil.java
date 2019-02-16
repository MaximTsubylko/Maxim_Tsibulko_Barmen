package com.tsibulko.finaltask.util.DBUtil;

import com.tsibulko.finaltask.dao.ConnectionPoolFactory;
import com.tsibulko.finaltask.dao.ConnectionPoolType;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class InMemoryDBUtil {
    public static void fill() throws DaoException, IOException, SQLException, InterruptedException, ConnectionPoolException {
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

    public static void drop() throws SQLException, IOException, ConnectionPoolException, InterruptedException {
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