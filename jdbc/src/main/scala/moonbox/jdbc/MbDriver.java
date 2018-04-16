package moonbox.jdbc;

import moonbox.util.MoonboxJDBCUtils;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class MbDriver implements java.sql.Driver {
    static {
        try {
            DriverManager.registerDriver(new MbDriver());
        } catch (SQLException e) {
            throw new RuntimeException("Can't register MbDriver!");
        }
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        MoonboxConnection conn = new MoonboxConnection(url, info);
        if (conn.userCheck()) {
            return conn;
        } else {
            throw new SQLException("User check error");
        }
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        if (url == null)
            return false;
        return MoonboxJDBCUtils.parseURL(url, null) != null;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
        return null;
    }
}