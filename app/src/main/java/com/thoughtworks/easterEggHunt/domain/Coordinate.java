package com.thoughtworks.easterEggHunt.domain;

public class Coordinate {

    private Double xCoord;
    private Double yCoord;

    public Coordinate(Double xCoord, Double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Double getxCoord() {
        return xCoord;
    }

    public void setxCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    public Double getyCoord() {
        return yCoord;
    }

    public void setyCoord(Double yCoord) {
        this.yCoord = yCoord;
    }
}
