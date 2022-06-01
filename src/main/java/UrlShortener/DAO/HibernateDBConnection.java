package UrlShortener.DAO;

import UrlShortener.Entity.UrlTable;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.sql.Timestamp;

public class HibernateDBConnection implements DAO {
    private static final Logger log = Logger.getLogger(HibernateDBConnection.class);
    public Configuration configuration;
    public SessionFactory factory;

    {
        configuration = new Configuration();
        configuration.getProperties();
        factory = configuration
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
    @Override
    public void insertDataIntoDB(String hash, String originalUrl, Timestamp timestamp) {
        log.info("Используется Hibernate");
        try {
            Session session = factory.openSession();
            UrlTable urlTable = new UrlTable(hash, originalUrl, timestamp);
            session.beginTransaction();
            session.save(urlTable);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    @Override
    public String getFullUrl(String uuid) {
        log.info("Используется Hibernate");
        UrlTable urlTable;
        try {
            Session session = factory.openSession();
            Query query = session.createQuery("from UrlTable as u where u.hash = :uuid");
            query.setParameter("uuid", uuid);
            urlTable = (UrlTable) query.getSingleResult();
        } finally {
            factory.close();
        }
        return urlTable.getOriginalUrl();
    }
}