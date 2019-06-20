package com.rollingstone.domain;

import java.util.Objects;

public class Store {

    private long id;
    private String storeNumber;
    private String storeStreetAddress;
    private String storeHourseNumber;
    private String storeCity;
    private String storeState;
    private String storeZipCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getStoreStreetAddress() {
        return storeStreetAddress;
    }

    public void setStoreStreetAddress(String storeStreetAddress) {
        this.storeStreetAddress = storeStreetAddress;
    }

    public String getStoreHourseNumber() {
        return storeHourseNumber;
    }

    public void setStoreHourseNumber(String storeHourseNumber) {
        this.storeHourseNumber = storeHourseNumber;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public String getStoreZipCode() {
        return storeZipCode;
    }

    public void setStoreZipCode(String storeZipCode) {
        this.storeZipCode = storeZipCode;
    }

    public Store(long id, String storeNumber, String storeStreetAddress, String storeHourseNumber, String storeCity, String storeState, String storeZipCode) {
        this.id = id;
        this.storeNumber = storeNumber;
        this.storeStreetAddress = storeStreetAddress;
        this.storeHourseNumber = storeHourseNumber;
        this.storeCity = storeCity;
        this.storeState = storeState;
        this.storeZipCode = storeZipCode;
    }

    public Store() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return id == store.id &&
                Objects.equals(storeNumber, store.storeNumber) &&
                Objects.equals(storeStreetAddress, store.storeStreetAddress) &&
                Objects.equals(storeHourseNumber, store.storeHourseNumber) &&
                Objects.equals(storeCity, store.storeCity) &&
                Objects.equals(storeState, store.storeState) &&
                Objects.equals(storeZipCode, store.storeZipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeNumber, storeStreetAddress, storeHourseNumber, storeCity, storeState, storeZipCode);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeNumber='" + storeNumber + '\'' +
                ", storeStreetAddress='" + storeStreetAddress + '\'' +
                ", storeHourseNumber='" + storeHourseNumber + '\'' +
                ", storeCity='" + storeCity + '\'' +
                ", storeState='" + storeState + '\'' +
                ", storeZipCode='" + storeZipCode + '\'' +
                '}';
    }
}
