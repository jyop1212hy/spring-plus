package org.example.expert.domain.todo.weatherEnum;

public enum Weather {

    SUNNY,
    RAINY,
    CLOUDY,
    SNOWY;

    public static Weather fromExternal(String external) {

        if(external == null || external.isBlank()) {
            throw new IllegalArgumentException("Weather is null or empty");
        }

        String lowerCase = external.toLowerCase();
        if(lowerCase.contains("sun")) {return SUNNY;}
        if(lowerCase.contains("cloud")) {return CLOUDY;}
        if(lowerCase.contains("rain")) {return RAINY;}
        if(lowerCase.contains("snow")) {return SNOWY;}

        throw new IllegalArgumentException("Unknown weather: " + external);
        }
    }
