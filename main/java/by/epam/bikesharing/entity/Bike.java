package by.epam.bikesharing.entity;

import java.util.StringJoiner;

public class Bike implements BaseEntity {

    private long id;
    private long spotId;
    private long userId;
    private String serialNumber;
    private BikeModel model;

    public Bike() {}

    public Bike(long id, String serialNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getSpotId() {
        return spotId;
    }

    public void setSpotId(long spotId) {
        this.spotId = spotId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BikeModel getModel() {
        return model;
    }

    public void setModel(BikeModel model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Bike.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("spotId=" + spotId)
                .add("userId=" + userId)
                .add("serialNumber='" + serialNumber + "'")
                .add("model=" + model)
                .toString();
    }
}