package UrlShortener;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Service {
    public String processShortener(String body) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(createdAt);
        try {
            DataBaseRequest dataBaseRequest = new DataBaseRequest();
            dataBaseRequest.insertDataIntoDB(uuid.toString(), body, timestamp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Constants.HOST + uuid);
    }

    public String processRedirect(String uuid) {
        String fullUrl = null;
        try {
            DataBaseRequest dataBaseRequest = new DataBaseRequest();
            fullUrl = dataBaseRequest.getFullUrl(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullUrl;
    }
}