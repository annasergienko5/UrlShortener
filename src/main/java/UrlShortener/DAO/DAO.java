package UrlShortener.DAO;

import java.sql.Timestamp;

public interface DAO {
    void insertDataIntoDB(String hash, String originalUrl, Timestamp timestamp);

    String getFullUrl(String uuid);
}