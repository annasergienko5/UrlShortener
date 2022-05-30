package UrlShortener;

import java.sql.*;

public class DataBaseRequest {
    private static Connection connection = null;

    public DataBaseRequest() throws SQLException {
        connection = DataBaseSource.getConnection();
    }

    public void insertDataIntoDB(String hash, String originalUrl, Timestamp timestamp) {
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

    public String getFullUrl(String uuid) {
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