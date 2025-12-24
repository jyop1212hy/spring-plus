package org.example.expert.domain.todo.weatherEnum;

public enum Weather {

    SUNNY,
    RAINY,
    CLOUDY,
    SNOWY;

    public static Weather fromExternal(String external) {
        if (external == null){return null;}

        String lowerCase = external.trim().toLowerCase();

        return switch (lowerCase) {
            case "sunny", "warm" -> SUNNY;
            case "rainy" -> RAINY;
            case "cloudy" -> CLOUDY;
            case "snowy" -> SNOWY;
            default -> throw new IllegalArgumentException("Unknown weather: " + external);
        };
    }
}
