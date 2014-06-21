package com.thoughtworks.easterEggHunt;

import com.thoughtworks.easterEggHunt.domain.Coordinate;
import retrofit.RestAdapter;

import java.util.List;

public class CoordinateService {

    private final RestAdapter restAdapter;
    private CoordinateClient coordinateClient;

    public CoordinateService() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.1.3:8080/grapp")
                .build();
        coordinateClient = restAdapter.create(CoordinateClient.class);
    }

    public Coordinate getCoordinate() {
        return coordinateClient.getCoordinate();
    }

    public List<Coordinate> myCoordinates() {
        return coordinateClient.myCoordinates("jered");
    }
}
