package tech.mohammad.amir.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import tech.mohammad.amir.dto.OccupancyRequestDTO;
import tech.mohammad.amir.dto.OccupancyResponseDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static tech.mohammad.amir.TestUtils.getOccupancyRequest;

@RunWith(MockitoJUnitRunner.class)
public class OccupancyOptimizationServiceTest {

    @InjectMocks
    private OccupancyOptimizationService occupancyOptimizationService;

    @Test
    public void testShouldOptimizeOccupancyWithLessPremiumRoomLessEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(3, 3);

        OccupancyResponseDTO response = occupancyOptimizationService.optimizeOccupancy(occupancyRequest);

        verifyResponse(response, 3, 3, 738, 167);
    }

    @Test
    public void testShouldOptimizeOccupancyWithMorePremiumRoomLessEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(7, 5);

        OccupancyResponseDTO response = occupancyOptimizationService.optimizeOccupancy(occupancyRequest);

        verifyResponse(response, 6, 4, 1054, 189);
    }

    @Test
    public void testShouldOptimizeOccupancyWithLessPremiumRoomMoreEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(2, 7);

        OccupancyResponseDTO response = occupancyOptimizationService.optimizeOccupancy(occupancyRequest);

        verifyResponse(response, 2, 4, 583, 189);
    }

    @Test
    public void testShouldOptimizeOccupancyWithEqualPremiumRoomLessEconomyRoom() {
        OccupancyRequestDTO occupancyRequest = getOccupancyRequest(7, 1);

        OccupancyResponseDTO response = occupancyOptimizationService.optimizeOccupancy(occupancyRequest);


        verifyResponse(response, 7, 1, 1153, 45);
    }

    private void verifyResponse(OccupancyResponseDTO response,
                                Integer usagePremium,
                                Integer usageEconomy,
                                Integer totalEarningPremium,
                                Integer totalEarningEconomy
    ) {
        assertNotNull(response);

        assertEquals(usagePremium, response.getUsagePremium());
        assertEquals(totalEarningPremium, response.getTotalEarningPremium());
        assertEquals(usageEconomy, response.getUsageEconomy());
        assertEquals(totalEarningEconomy, response.getTotalEarningEconomy());
    }
}
