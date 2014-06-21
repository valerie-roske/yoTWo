package com.thoughtworks.easterEggHunt;

import com.thoughtworks.easterEggHunt.domain.Coordinate;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

public interface CoordinateClient {
    @GET("/poop")
    Coordinate getCoordinate();

    @GET("/poop/myCoordinates/{name}")
    List<Coordinate> myCoordinates(@Path("name") String name);
}
