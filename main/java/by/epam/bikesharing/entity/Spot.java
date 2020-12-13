package by.epam.bikesharing.entity;

import java.util.StringJoiner;

public class Spot implements BaseEntity {

    private long id;
    private String address;
    private double lat;
    private double lng;

    public Spot() {}

    public Spot(long id, String address, double lat, double lng) {
        this.id = id;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String title) {
        this.address = title;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Spot.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("address='" + address + "'")
                .add("lat=" + lat)
                .add("lng=" + lng)
                .toString();
    }
}