package CapstoneDesign.EvacuationGuide.usefulMethod;

public class Haversine {
    double distance;
    double radius = 6371; // 지구 반지름(km)
    double toRadian = Math.PI / 180;

    public double distanceInKilometerByHaversine(double latitude1, double longitude1, double latitude2, double longitude2) {
        double deltaLatitude = Math.abs(latitude1 - latitude2) * toRadian;
        double deltaLongitude = Math.abs(longitude1 - longitude2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
                sinDeltaLat * sinDeltaLat +
                        Math.cos(latitude1 * toRadian) * Math.cos(latitude2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);

        return distance;
    }

}
