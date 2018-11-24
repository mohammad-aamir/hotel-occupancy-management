package tech.mohammad.amir;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import tech.mohammad.amir.dto.OccupancyRequestDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static tech.mohammad.amir.TestUtils.getOccupancyRequest;
import static tech.mohammad.amir.TestUtils.toJsonString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelRoomOccupancyOptimizationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelRoomOccupancyOptimizationIntegrationTest {
    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void testShouldOptimizeOccupancyWithLessPremiumRoomLessEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(3, 3);

        ValidatableResponse validatableResponse = getResponse(occupancyRequest);

        verifyRestResponse(validatableResponse, 3, 3, 738, 167);
    }

    @Test
    public void testShouldOptimizeOccupancyWithMorePremiumRoomLessEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(7, 5);

        ValidatableResponse validatableResponse = getResponse(occupancyRequest);

        verifyRestResponse(validatableResponse, 6, 4, 1054, 189);
    }

    @Test
    public void testShouldOptimizeOccupancyWithLessPremiumRoomMoreEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(2, 7);

        ValidatableResponse validatableResponse = getResponse(occupancyRequest);

        verifyRestResponse(validatableResponse, 2, 4, 583, 189);
    }

    @Test
    public void testShouldOptimizeOccupancyWithEqualPremiumRoomLessEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(7, 1);

        ValidatableResponse validatableResponse = getResponse(occupancyRequest);

        verifyRestResponse(validatableResponse, 7, 1, 1153, 45);
    }

    private void verifyRestResponse(
            ValidatableResponse validatableResponse,
            int usagePremium,
            int usageEconomy,
            int earningPremium,
            int earningEconomy
    ) {
        validatableResponse.statusCode(HttpStatus.OK.value());
        validatableResponse.body("usagePremium", equalTo(usagePremium));
        validatableResponse.body("usageEconomy", equalTo(usageEconomy));
        validatableResponse.body("totalEarningPremium", equalTo(earningPremium));
        validatableResponse.body("totalEarningEconomy", equalTo(earningEconomy));
    }

    private ValidatableResponse getResponse(OccupancyRequestDTO occupancyRequest) {
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(toJsonString(occupancyRequest))
                .when()
                .post("/v1/optimizeOccupancy")
                .prettyPeek()
                .then();
    }
}
