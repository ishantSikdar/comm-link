package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.others.Coordinates;

public class GeoLocation {

    private static final Double R = 6371.0;

    public static double calculateDistance(Coordinates coord1, Coordinates coord2) {
        // Radius of the Earth in kilometers
        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(coord1.getLatitude());
        double lon1Rad = Math.toRadians(coord1.getLongitude());
        double lat2Rad = Math.toRadians(coord2.getLatitude());
        double lon2Rad = Math.toRadians(coord2.getLongitude());

        // Calculate the differences
        double latDiff = lat2Rad - lat1Rad;
        double lonDiff = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.pow(Math.sin(latDiff / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(lonDiff / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance
        return R * c;
    }


    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Radius of the Earth in kilometers
        final double R = 6371.0;

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences
        double latDiff = lat2Rad - lat1Rad;
        double lonDiff = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.pow(Math.sin(latDiff / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(lonDiff / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance

        return R * c;
    }
}
