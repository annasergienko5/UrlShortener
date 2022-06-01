package UrlShortener.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "url")
public class UrlTable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String hash;

    private String originalUrl;

    private Timestamp createdAt;

    public UrlTable() {
    }

    public UrlTable(String hash, String originalUrl, Timestamp createdAt) {
        this.hash = hash;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Url{" +
                "hash=" + hash +
                ", originalUrl='" + originalUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}