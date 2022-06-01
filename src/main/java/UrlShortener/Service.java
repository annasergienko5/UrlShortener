package UrlShortener;

import UrlShortener.DAO.HibernateDBConnection;
import UrlShortener.DAO.DAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Service {
    public String processShortener(String body) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(createdAt);
        DAO dao = new HibernateDBConnection();
        dao.insertDataIntoDB(uuid.toString(), body, timestamp);
        return (Constants.HOST + uuid);
    }

    public String processRedirect(String uuid) {
        String fullUrl;
        DAO dao = new HibernateDBConnection();
        fullUrl = dao.getFullUrl(uuid);
        return fullUrl;
    }
}