package tech.mohammad.amir;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import tech.mohammad.amir.dto.OccupancyRequestDTO;

import java.util.Map;

public class TestUtils {
    public static final String GUEST_FILE_NAME = "guests.json";
    public static final String INVALID_FILE_NAME = "invalid.json";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static OccupancyRequestDTO getOccupancyRequest(Integer freePremiumRooms, Integer freeEconomyRooms) {
        OccupancyRequestDTO occupancyRequestDTO = new OccupancyRequestDTO();
        occupancyRequestDTO.setFreeEconomyRooms(freeEconomyRooms);
        occupancyRequestDTO.setFreePremiumRooms(freePremiumRooms);

        return occupancyRequestDTO;
    }

    public static String toJsonString(Object object) {
        return new JSONObject(objectMapper.convertValue(object, Map.class)).toString();
    }
}
