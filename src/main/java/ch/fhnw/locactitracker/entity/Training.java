package ch.fhnw.locactitracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.PartitionKey;
import info.archinnov.achilles.annotations.Table;

import javax.validation.constraints.NotNull;

/**
 * Training trace
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(keyspace = "locactitracker", table="Training")
public class Training {

    @PartitionKey(1)
    @NotNull
    private String user;

    @PartitionKey(2)
    @NotNull
    private Long timestamp;

    @Column
    @NotNull
    private String activity;

    @Column
    @NotNull
    private Boolean dominantHand;

    @Column
    @NotNull
    private Double x;

    @Column
    @NotNull
    private Double y;

    @Column
    @NotNull
    private Double z;

    @Column
    @NotNull
    private Double longitude;

    @Column
    @NotNull
    private Double latitude;

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public void setDominantHand (Boolean dominantHand) { this.dominantHand = dominantHand; }

    public Boolean getDominantHand () { return this.dominantHand; }

    public String getActivity() { return activity; }

    public void setActivity(String activity) { this.activity = activity; }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "[timestamp: " + timestamp + ", x: " + x + ", y: " + y + ", z: " + z + ", long: " + longitude + ", lat: " + latitude + ", user:" + user + ", dominantHand: " + dominantHand + "]";
    }
}
