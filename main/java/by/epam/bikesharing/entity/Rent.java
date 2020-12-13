package by.epam.bikesharing.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.StringJoiner;

public class Rent implements BaseEntity {

    private long id;
    private long userId;
    private long bikeId;
    private Timestamp start;
    private Timestamp end;
    private BigDecimal cost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBikeId() {
        return bikeId;
    }

    public void setBikeId(long bikeId) {
        this.bikeId = bikeId;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Rent.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("bikeId=" + bikeId)
                .add("start=" + start)
                .add("end=" + end)
                .add("cost=" + cost)
                .toString();
    }
}