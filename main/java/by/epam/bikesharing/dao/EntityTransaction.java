package by.epam.bikesharing.dao;

import by.epam.bikesharing.sqlpool.ConnectionPool;
import by.epam.bikesharing.sqlpool.ProxyConnection;

import java.sql.SQLException;

public class EntityTransaction {

    private ProxyConnection connection;

    public void begin(AbstractDao dao, AbstractDao... daos) {
        if (connection == null) {
            connection = ConnectionPool.INSTANCE.getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao.setConnection(connection);
        for (AbstractDao DAO: daos) {
            DAO.setConnection(connection);
        }
    }

    public void end() {
        if (connection == null) return;
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            //log
        }
        ConnectionPool.INSTANCE.releaseConnection(connection);
        connection = null;
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            //log
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //log
        }
    }
}
