package by.epam.bikesharing.entity;

public interface BaseEntity {


    default boolean isNull() {
        return true;
    }
}