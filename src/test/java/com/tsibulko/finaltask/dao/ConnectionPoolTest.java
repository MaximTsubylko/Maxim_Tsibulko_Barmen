package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.dao.ConnectionPool;
import com.tsibulko.finaltask.dao.ConnectionPoolFactory;
import com.tsibulko.finaltask.dao.ConnectionPoolType;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.impl.JDBCConnectionPool;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ConnectionPoolTest {
    private static final int N_THREADS = 5;
    private static final int POOL_CAPACITY = 8;
    private static final String JDBCDRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";
    private static final String DB_URL = "jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    @Test
    public void shouldGetConnection() throws InterruptedException, IOException {

        ConnectionPool connectionPool = Mockito.spy(ConnectionPoolFactory.getInstance().getConnectionPool());
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        Set<Integer> hashCodes = Collections.synchronizedSet(new HashSet<>());

        IntStream.range(0, N_THREADS).forEach(i -> executorService.submit(() -> {
            try (Connection connection = connectionPool.retrieveConnection()) {
                Thread.sleep(10L);
                Assert.assertTrue(connection instanceof Proxy);
                int hashCode = connection.hashCode();
                hashCodes.add(hashCode);
            } catch (SQLException | IllegalStateException e) {
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }));
        executorService.awaitTermination(5L, TimeUnit.SECONDS);
        Assert.assertEquals(POOL_CAPACITY, hashCodes.size());
        Mockito.verify(((JDBCConnectionPool) connectionPool),
                Mockito.times(N_THREADS)).putBackConnection(Mockito.any());
    }
}
