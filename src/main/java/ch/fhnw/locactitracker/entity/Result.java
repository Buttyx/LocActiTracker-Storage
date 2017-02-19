package ch.fhnw.locactitracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import info.archinnov.achilles.annotations.Column;
import info.archinnov.achilles.annotations.PartitionKey;
import info.archinnov.achilles.annotations.Table;

import javax.validation.constraints.NotNull;

/**
 * Recognition result
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(keyspace = "locactitracker", table="Result")
public class Result{

    @PartitionKey(1)
    @NotNull
    private String user;

    @Column
    @NotNull
    private String activity;

    @Column
    @NotNull
    private Boolean locationAware;

    @Column
    @NotNull
    private Boolean dominantHand;

    @PartitionKey(3)
    @NotNull
    private String model;

    @PartitionKey(2)
    @NotNull
    private Long timestamp;

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivity() {return activity;}
    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getModel() {return model;}
    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getLocationAware() {return locationAware;}
    public void setLocationAware(Boolean locationAware) {
        this.locationAware = locationAware;
    }

    public Boolean getDominantHand() {return dominantHand;}
    public void setDominantHand(Boolean dominantHand) {
        this.dominantHand = dominantHand;
    }
}
