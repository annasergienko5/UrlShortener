package UrlShortener.DAO;

import org.apache.log4j.Logger;

import java.sql.*;

public class JDBCRequests implements DAO {
    private static final Logger log = Logger.getLogger(JDBCRequests.class);
    private static Connection connection = null;

    public JDBCRequests() throws SQLException {
        connection = DataBaseSource.getConnection();
    }

    @Override
    public void insertDataIntoDB(String hash, String originalUrl, Timestamp timestamp) {
        log.info("Используется JDBC");
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO url (hash, originalUrl, createdAt) " +
                    "VALUES (?, ?, ?)");
            stmt.setString(1, hash);
            stmt.setString(2, originalUrl);
            stmt.setTimestamp(3, timestamp);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFullUrl(String uuid) {
        log.info("Используется JDBC");
        String fullUrl = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select originalurl from public.url u \n" +
                    "where hash = ?");
            stmt.setString(1, uuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                fullUrl = rs.getString("originalurl");
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullUrl;
    }
}