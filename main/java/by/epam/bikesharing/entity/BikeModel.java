package by.epam.bikesharing.entity;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class BikeModel implements BaseEntity {

    private long id;
    private String name;
    private String imageUrl;
    private BigDecimal cost;

    public BikeModel() {}

    public BikeModel(long id, String name, String imageUrl, BigDecimal cost) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BikeModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("imageUrl='" + imageUrl + "'")
                .add("cost=" + cost)
                .toString();
    }
}