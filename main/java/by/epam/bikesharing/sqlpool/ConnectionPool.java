package by.epam.bikesharing.sqlpool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {

    INSTANCE;

    private String url = "jdbc:sqlserver://localhost:1433;databaseName=Bikesharing";
    private String user = "sa";
    private String password = "3110clas";
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> usedConnections;
    private final static int MAX_POOL_SIZE = 32;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(MAX_POOL_SIZE);
        usedConnections = new ArrayDeque<>();
    }

    public ProxyConnection getConnection() {
        if (freeConnections.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                try {
                    freeConnections.offer(createConnection(url, user, password));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        if (usedConnections.contains(connection)) {
            usedConnections.remove(connection);
            freeConnections.offer(connection);
        }
    }

    public void destroyPool() {
        usedConnections.forEach(this::releaseConnection);
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            try {
                freeConnections.take().closeInternalConnection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private ProxyConnection createConnection(String url, String user, String password) throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(url, user, password));
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}